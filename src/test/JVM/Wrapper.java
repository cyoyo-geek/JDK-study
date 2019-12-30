package test.JVM;

import java.util.Map;
import java.util.Random;

//java -Xmx100m -XX:+UseParallelGC Wrapper   //java.lang.OutOfMemoryError: GC overhead limit exceeded
//GC算法：-XX:+UseConcMarkSweepGC 或者-XX:+UseG1GC

//解决办法： -XX:-UseGCOverheadLimit  或者-Xmx1024m
public class Wrapper {
    public static void main(String args[]) throws Exception {
        Map map = System.getProperties();
        Random r = new Random();
        while (true) {
            map.put(r.nextInt(), "value");
        }

    }
}