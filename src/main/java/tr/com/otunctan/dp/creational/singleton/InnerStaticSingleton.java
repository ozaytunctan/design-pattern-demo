package tr.com.otunctan.dp.creational.singleton;

public class InnerStaticSingleton {


    private InnerStaticSingleton() {
    }

    public static InnerStaticSingleton getInstance() {
        return InnerStaticSingletonHolder.INSTANCE;
    }

    private static class InnerStaticSingletonHolder {
        private static InnerStaticSingleton INSTANCE = new InnerStaticSingleton();
    }
}

class InnerSingletonDemo {

    public static void main(String[] args) {
        InnerStaticSingleton in = InnerStaticSingleton.getInstance();
        InnerStaticSingleton in2 = InnerStaticSingleton.getInstance();
        System.out.println(in == in2);
    }
}
