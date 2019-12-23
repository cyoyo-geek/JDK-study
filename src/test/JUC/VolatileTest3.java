package test.JUC;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

public class VolatileTest3 {
    private static Config config = null;
    private static volatile boolean initialized = false;

    public static void main(String[] args) {
        // 线程1负责初始化配置信息
        new Thread(() -> {
            config = new Config();
            config.name = "config";
            initialized = true;
        }).start();

        // 线程2检测到配置初始化完成后使用配置信息
        new Thread(() -> {
            while (!initialized) {
                LockSupport.parkNanos(TimeUnit.MILLISECONDS.toNanos(100));
            }

            // do sth with config
            String name = config.name;
        }).start();
    }
}

class Config {
    String name;
}
