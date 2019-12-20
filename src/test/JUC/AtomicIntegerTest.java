package test.JUC;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

public class AtomicIntegerTest {
    private static AtomicInteger count = new AtomicInteger(0);

    public static void increment() {
        count.incrementAndGet();
    }

    public static void main(String[] args) {
        IntStream.range(0, 100)
                .forEach(i->
                        new Thread(()-> IntStream.range(0, 1000)
                                .forEach(j->increment())).start());

        // 这里使用2或者1看自己的机器
        // 我这里是用run跑大于2才会退出循环
        // 但是用debug跑大于1就会退出循环了
        while (Thread.activeCount() > 2) {
            // 让出CPU
            Thread.yield();
        }

        System.out.println(count);
    }
}