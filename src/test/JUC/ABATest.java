package test.JUC;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;
import java.util.concurrent.locks.LockSupport;

public class ABATest {
    static class Stack {
        // 将top放在原子类中
        private AtomicReference<Node> top = new AtomicReference<>();

        // 栈中节点信息
        static class Node {
            int value;
            Node next;

            public Node(int value) {
                this.value = value;
            }
        }

        // 出栈操作
        public Node pop() {
            for (; ; ) {
                // 获取栈顶节点
                Node t = top.get();
                if (t == null) {
                    return null;
                }
                // 栈顶下一个节点
                Node next = t.next;
                // CAS更新top指向其next节点
                if (top.compareAndSet(t, next)) {
                    // 把栈顶元素弹出，应该把next清空防止外面直接操作栈
                    t.next = null;
                    return t;
                }
            }
        }

        // 入栈操作
        public void push(Node node) {
            for (; ; ) {
                // 获取栈顶节点
                Node next = top.get();
                // 设置栈顶节点为新节点的next节点
                node.next = next;
                // CAS更新top指向新节点
                if (top.compareAndSet(next, node)) {
                    return;
                }
            }
        }
    }
    private static void testStack() {
        // 初始化栈为 top->1->2->3
        Stack stack = new Stack();
        stack.push(new Stack.Node(3));
        stack.push(new Stack.Node(2));
        stack.push(new Stack.Node(1));

        new Thread(()->{
            // 线程1出栈一个元素
            stack.pop();
        }).start();

        new Thread(()->{
            // 线程2出栈两个元素
            Stack.Node A = stack.pop();
            Stack.Node B = stack.pop();
            // 线程2又把A入栈了
            stack.push(A);
        }).start();

    }
    private static void Test1(){
        AtomicInteger atomicInteger = new AtomicInteger(1);

        new Thread(()->{
            int value = atomicInteger.get();
            System.out.println("thread 1 read value: " + value);

            // 阻塞1s
            LockSupport.parkNanos(1000000000L);

            if (atomicInteger.compareAndSet(value, 3)) {
                System.out.println("thread 1 update from " + value + " to 3");
            } else {
                System.out.println("thread 1 update fail!");
            }
        }).start();

        new Thread(()->{
            int value = atomicInteger.get();
            System.out.println("thread 2 read value: " + value);
            if (atomicInteger.compareAndSet(value, 2)) {
                System.out.println("thread 2 update from " + value + " to 2");

                // do sth

                value = atomicInteger.get();
                System.out.println("thread 2 read value: " + value);
                if (atomicInteger.compareAndSet(value, 1)) {
                    System.out.println("thread 2 update from " + value + " to 1");
                }
            }
        }).start();
    }

    private static void testStamp() {
        AtomicStampedReference<Integer> atomicStampedReference = new AtomicStampedReference<>(1, 1);

        new Thread(()->{
            int[] stampHolder = new int[1];
            int value = atomicStampedReference.get(stampHolder);
            int stamp = stampHolder[0];
            System.out.println("thread 1 read value: " + value + ", stamp: " + stamp);

            // 阻塞1s
            LockSupport.parkNanos(1000000000L);

            if (atomicStampedReference.compareAndSet(value, 3, stamp, stamp + 1)) {
                System.out.println("thread 1 update from " + value + " to 3");
            } else {
                System.out.println("thread 1 update fail!");
            }
        }).start();

        new Thread(()->{
            int[] stampHolder = new int[1];
            int value = atomicStampedReference.get(stampHolder);
            int stamp = stampHolder[0];
            System.out.println("thread 2 read value: " + value + ", stamp: " + stamp);
            if (atomicStampedReference.compareAndSet(value, 2, stamp, stamp + 1)) {
                System.out.println("thread 2 update from " + value + " to 2");

                // do sth

                value = atomicStampedReference.get(stampHolder);
                stamp = stampHolder[0];
                System.out.println("thread 2 read value: " + value + ", stamp: " + stamp);
                if (atomicStampedReference.compareAndSet(value, 1, stamp, stamp + 1)) {
                    System.out.println("thread 2 update from " + value + " to 1");
                }
            }
        }).start();
    }
    public static void main(String[] args) {
//        Test1();
//        testStack();
        testStamp();
    }
}

/**
 * （1）版本号
 * 比如，上面的栈结构增加一个版本号用于控制，每次CAS的同时检查版本号有没有变过。
 * 还有一些数据结构喜欢使用高位存储一个邮戳来保证CAS的安全。
 * （2）不重复使用节点的引用
 * 比如，上面的栈结构在线程2执行push()入栈操作的时候新建一个节点传入，而不是复用节点1的引用；
 * （3）直接操作元素而不是节点
 * 比如，上面的栈结构push()方法不应该传入一个节点（Node），而是传入元素值（int的value）。
 */