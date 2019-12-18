package test.util;
import java.util.LinkedHashMap;
import java.util.Map;

public class LinkedHashMapActivity {

    public static void MapSizeSold(){
        LinkedHashMap<Integer, Integer> linkedHashMap;
        linkedHashMap = new LinkedHashMap<Integer, Integer>(2);
        linkedHashMap.put(1, 1);
        linkedHashMap.put(2, 2);
        linkedHashMap.put(3, 3);

        for (Map.Entry<Integer, Integer> a : linkedHashMap.entrySet()) {
            System.out.println("key->" + a.getKey() + "");
            System.out.println("value->" + a.getValue() + "");
        }
    }
    public static void MapSizeChange(){
        //LRU缓存策略
        LinkedHashMap<Integer, Integer> linkedHashMap = new LinkedHashMap<Integer, Integer>(2,0.75f,true){
            @Override
            protected boolean removeEldestEntry(Map.Entry<Integer, Integer> entry) {
                return size()>2;
            }
        };
        linkedHashMap.put(1, 1);
        linkedHashMap.put(2, 2);
        //调用get进行排序 //LinkedHashMap 按访问顺序排序默认是关闭的，可通过构造函数实例化的时候打开
        linkedHashMap.get(1);

        //1. 先创建新的元素，通过 hash 计算位置并存放。
        //2. 每次 newNode 创建元素时都连带调用 linkNodeLast () 将新插入元素的双向链表关系维护起来（新插入的放末尾）。
        //3. 每次 put 新元素均通过 LinkedHashMap 中重写的 afterNodeInsertion () 判断是否删除头节点元素。
        linkedHashMap.put(3, 3);


        for (Map.Entry<Integer, Integer> a : linkedHashMap.entrySet()) {
            System.out.println("key->" + a.getKey() + "");
            System.out.println("value->" + a.getValue() + "");
        }
    }

    //LinkedHashMap 底层也是基于链表，如果 JDK1.8 以上就是链表 + 红黑树
    //LinkedHashMap 的不同之处就在于它又多维护了一个双向链表
    //如果 TreeNode 直接 extends Node 则需要 Node extentds LinkedHashMap.Entry<K, V>，Node 使用是很频繁的，这样空间浪费的就太多了
    //JDK1.8 当一个桶中的元素超过 8 才会转换为红黑树（TreeNode），<6 又转回链表（Node）
    public static void main(String[] args) {
        //LinkedHashMap 的容量是可控的。
        //LinkedHashMap 是有插入顺序的。
        MapSizeChange();
    }
}
//
//        （1）LinkedHashMap继承自HashMap，具有HashMap的所有特性；
//
//        （2）LinkedHashMap内部维护了一个双向链表存储所有的元素；
//
//        （3）如果accessOrder为false，则可以按插入元素的顺序遍历元素；
//
//        （4）如果accessOrder为true，则可以按访问元素的顺序遍历元素；
//
//        （5）LinkedHashMap的实现非常精妙，很多方法都是在HashMap中留的钩子（Hook），直接实现这些Hook就可以实现对应的功能了，并不需要再重写put()等方法；
//
//        （6）默认的LinkedHashMap并不会移除旧元素，如果需要移除旧元素，则需要重写removeEldestEntry()方法设定移除策略；
//
//        （7）LinkedHashMap可以用来实现LRU缓存淘汰策略；（Least Recently Used，最近最少使用，也就是优先淘汰最近最少使用的元素）