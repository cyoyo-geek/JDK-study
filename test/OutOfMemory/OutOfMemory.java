package OutOfMemory;

import java.util.HashMap;
import java.util.Map;


//-Xms24m -Xmx24m -XX:+HeapDumpOnOutOfMemoryError
//jmap -dump:live,format=b,file=<Filepath> <pid>
//JProfiler分析 VisualVM MAT

public class OutOfMemory {
    public static void main(String[] args) throws InterruptedException {
        Map<Integer ,byte[]> data = new HashMap<>();
        for (Integer i = 0; i<10000; i++){
//            Thread.sleep(1000);
            data.put(i, new byte[10000]);
        }
    }
}
