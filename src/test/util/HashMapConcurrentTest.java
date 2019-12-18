package test.util;

import java.util.HashMap;
import java.util.Map;

public class HashMapConcurrentTest {
    /**
     * NUMBER = 50，表示 50 个线程分别执行 put 方法 50 次
     * 线程安全的情况下因该 map size 应该为 2500
     */
    public static final int NUMBER = 50;
    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();
        for (int i = 0; i < NUMBER; i++) {
            new Thread(new HashMapTask(map)).start();
        }
        System.out.println("map size = " + map.size());
    }
}

class HashMapTask implements Runnable {

    Map<String, String> map;

    public HashMapTask(Map<String, String> map) {
        this.map = map;
    }

    @Override
    public void run() {
        for (int i = 0; i < HashMapConcurrentTest.NUMBER; i++) {
            map.put(i + "-" + Thread.currentThread().getName(), "test");
        }
    }
}