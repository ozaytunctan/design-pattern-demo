package tr.com.otunctan.dp.creational.singleton;

import java.util.UUID;

public class EagerSingleton {

    private static EagerSingleton INSTANCE = new EagerSingleton();

    private String guid = UUID.randomUUID().toString();

    public static EagerSingleton getInstance() {
        return INSTANCE;
    }

    @Override
    public String toString() {
        return "EagerSingleton{" +
                "guid='" + guid + '\'' +
                '}';
    }

    public static void main(String[] args) {
        EagerSingleton ins1 = EagerSingleton.getInstance();
        EagerSingleton ins2 = EagerSingleton.getInstance();
        EagerSingleton ins3 = EagerSingleton.getInstance();
        System.out.println(ins1);
        System.out.println(ins2);
        System.out.println(ins1 == ins3);
    }
}