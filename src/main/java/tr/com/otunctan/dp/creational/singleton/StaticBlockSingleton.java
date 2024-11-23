package tr.com.otunctan.dp.creational.singleton;

import java.io.*;

public class StaticBlockSingleton implements Serializable {

    private static StaticBlockSingleton INSTANCE;

    static {
        INSTANCE = new StaticBlockSingleton();
    }

    public static StaticBlockSingleton getInstance() {
        return INSTANCE;
    }

    static void saveToFile(StaticBlockSingleton singleton, String filename)
            throws Exception {
        try (FileOutputStream fileOut = new FileOutputStream(filename);
             ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
            out.writeObject(singleton);
        }
    }

    static StaticBlockSingleton readFromFile(String filename)
            throws Exception {
        try (FileInputStream fileIn = new FileInputStream(filename);
             ObjectInputStream in = new ObjectInputStream(fileIn)) {
            return (StaticBlockSingleton) in.readObject();
        }
    }

    public static void main(String[] args) throws Exception {
        StaticBlockSingleton instance = StaticBlockSingleton.getInstance();
        String fileName = "staticBlock.bin";

        StaticBlockSingleton.saveToFile(instance, fileName);

        StaticBlockSingleton instance2 = StaticBlockSingleton.getInstance();

        StaticBlockSingleton staticBlockSingleton = readFromFile(fileName);

        System.out.println(instance.equals(staticBlockSingleton));

    }
}
