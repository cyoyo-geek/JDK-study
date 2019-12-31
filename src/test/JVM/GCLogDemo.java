package test.JVM;

/**
 * GC日志演示
 * @author Jason
 * Parallel Scavenge垃圾收集器的新生代和使用Parallel old垃圾收集器的老生代
 * jvm开启的模式是Server，而Server模式的默认垃圾收集器组合
 * client模式，默认组合是Serial收集器和Serial Old收集器组合
 */
public class GCLogDemo {

    public static void main(String[] args) {
        int _1m = 1024 * 1024;
        byte[] data = new byte[_1m];
        // 将data置为null即让它成为垃圾
        data = null;
        // 通知垃圾回收器回收垃圾
        System.gc();
    }
}
