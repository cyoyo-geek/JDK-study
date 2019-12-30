package test.JVM;

import java.util.HashMap;
import java.util.Map;


//-Xms24m -Xmx24m -XX:+HeapDumpOnOutOfMemoryError
//jmap -dump:live,format=b,file=<Filepath> <pid>
//JProfiler分析 VisualVM MAT
//-XX:+PrintGCDetails
//-verbose:gc
//-XX:+printGC
//-XX:+PrintGCTimeStamps
//-Xloggc:log/gc.log
//-XX:+PrintHeapAtGC
//-XX:+TraceClassLoading
//-XX:+PrintClassHistogram
//-Xmx –Xms：指定最大堆和最小堆
//System.out.println("Xmx=" + Runtime.getRuntime().maxMemory() / 1024.0 / 1024 + "M");     //系统的最大空间
//System.out.println("free mem=" + Runtime.getRuntime().freeMemory() / 1024.0 / 1024 + "M");   //系统的空闲空间
//System.out.println("total mem=" + Runtime.getRuntime().totalMemory() / 1024.0 / 1024 + "M");   //当前可用的总空间

//java -XX:+PrintCommandLineFlags -version
//java -XX:+PrintGCDetails -version
public class OutOfMemory {
    public static void main(String[] args) throws InterruptedException {
        Map<Integer ,byte[]> data = new HashMap<>();
        for (Integer i = 0; i<10000; i++){
//            Thread.sleep(1000);
            data.put(i, new byte[10000]);
        }
    }
}
