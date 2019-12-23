package test.JUC;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class TestThreadPool {

    public static void main(String[] args) {
        //初始化ThreadPoolExecutor类，这里给ThreadPoolExecutor类提供很多很多参数
        ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 10, 200, TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<Runnable>(5));

        for(int i=0;i<12;i++){
            //定义了一个任务
            Task task = new Task(i);
            //往这个池进行提交任务
            executor.execute(task);
            //打印一些线程池的信息，就单纯的查看一下
            System.out.println("线程池中线程数目："+executor.getPoolSize()+"，队列中等待执行的任务数目："+ executor.getQueue().size()+"，已执行的任务数目："+executor.getCompletedTaskCount());
        }
        //关闭线程池，不再接受submit
        executor.shutdown();
    }
}


class Task implements Runnable {
    private int num;

    public Task(int num) {
        this.num = num;
    }
    @Override
    public void run() {
        System.out.println("正在执行task "+num);
        try {
            Thread.currentThread().sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("task "+num+"执行完毕");
    }
}