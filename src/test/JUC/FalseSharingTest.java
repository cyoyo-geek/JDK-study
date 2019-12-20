package test.JUC;

public class FalseSharingTest {
    public static void main(String[] args) throws InterruptedException{
        testPointer(new Pointer());
    }
    private static void testPointer(Pointer pointer) throws InterruptedException {
        long start = System.currentTimeMillis();
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 100000000; i++) {
                pointer.x++;
            }
        });
        Thread t2 = new Thread(() -> {
                    for (int i = 0; i < 100000000; i++) {
                        pointer.y++;
                    }
        });

        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(System.currentTimeMillis()-start);
        System.out.println(pointer);
    }

}
//
class Pointer
{
    volatile long x;
    long p1, p2, p3, p4, p5, p6, p7;  // 去除伪共享
    volatile long y;
}
//
class Pointer1 {
    MyLong x = new MyLong();
    MyLong y = new MyLong();
}
class MyLong {
    volatile long value;
    long p1, p2, p3, p4, p5, p6, p7;
}
//-XX:-RestrictContended
@sun.misc.Contended
class MyLong1 {
    volatile long value;
}

//
//（1）CPU具有多级缓存，越接近CPU的缓存越小也越快；
//（2）CPU缓存中的数据是以缓存行为单位处理的；
//（3）CPU缓存行能带来免费加载数据的好处，所以处理数组性能非常高；
//（4）CPU缓存行也带来了弊端，多线程处理不相干的变量时会相互影响，也就是伪共享；
//（5）避免伪共享的主要思路就是让不相干的变量不要出现在同一个缓存行中；
//（6）一是每两个变量之间加七个 long 类型；
//（7）二是创建自己的 long 类型，而不是用原生的；
//（8）三是使用 java8 提供的注解；