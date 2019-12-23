package test.JUC;

public class VolatileTest {
    // public static int finished = 0;
    public static volatile int finished = 0;

    private static void checkFinished() {
        while (finished == 0) {
            // do nothing
        }
        System.out.println("finished");
    }

    private static void finish() {
        finished = 1;
    }

    public static void main(String[] args) throws InterruptedException {
        // 起一个线程检测是否结束
        new Thread(() -> checkFinished()).start();

        Thread.sleep(100);

        // 主线程将finished标志置为1
        finish();

        System.out.println("main finished");

    }
}