package tr.com.otunctan.dp.creational.singleton;

public class ThreadSafetySingleton {

    private static ThreadSafetySingleton INSTANCE;

    private ThreadSafetySingleton() {
    }

    public synchronized static ThreadSafetySingleton getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ThreadSafetySingleton();
        }
        return INSTANCE;
    }
}

class Demo {

    public static void main(String[] args) {
        ThreadSafetySingleton instance = ThreadSafetySingleton.getInstance();
        ThreadSafetySingleton instance2 = ThreadSafetySingleton.getInstance();
        System.out.println(instance2.equals(instance));


    }
}
