package test.JUC;

public class SynchronizedTest {

    public static void sync() {
        synchronized (SynchronizedTest.class) {
            synchronized (SynchronizedTest.class) {
            }
        }
    }
//
//    public static void funcFor(){
//        for(;;){
//
//        }
//    }
//    public static void funcWhile(){
//        while(true){
//
//        }
//    }

    public static void main(String[] args) {
    }
}