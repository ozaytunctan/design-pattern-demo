package tr.com.otunctan.dp.creational.singleton;

public class DoubleCheckSingleton {

    private static volatile DoubleCheckSingleton INSTANCE;

    public static DoubleCheckSingleton getInstance() {
        if (INSTANCE == null) {
            synchronized (DoubleCheckSingleton.class) {
                if (INSTANCE == null) {
                    INSTANCE = new DoubleCheckSingleton();
                    return INSTANCE;
                }
            }
        }
        return INSTANCE;
    }
}

class DoubleCheckSingletonDemo {

    public static void main(String[] args) {

        DoubleCheckSingleton singleton = DoubleCheckSingleton.getInstance();
        DoubleCheckSingleton singleton2 = DoubleCheckSingleton.getInstance();
        System.out.println(singleton2 == singleton);
    }
}
