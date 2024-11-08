package tr.com.otunctan.solid.srp;


import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.attribute.FileAttribute;
import java.util.ArrayList;
import java.util.List;

/**
 * Single Responsibility Principle (SRP) Tek sorumluluk ilkesi
 */
public class Journal {
    private final List<String> entries = new ArrayList<>();

    private static int count = 0;

    public void addEntry(String text)
    {
        entries.add("" + (++count) + ": " + text);
    }

    public void removeEntry(int index)
    {
        entries.remove(index);
    }

    @Override
    public String toString() {
        return String.join(System.lineSeparator(), entries);
    }

    // here we break SRP
    public void save(String filename) throws Exception
    {
        try (PrintStream out = new PrintStream(filename))
        {
            out.println(toString());
        }
    }

    public Journal load(String filename) {
        return null;
    }
    public Journal load(URL url) {
        return null;
    }
}

// handles the responsibility of persisting objects
class JournalPersistence
{
    public void saveToFile(Journal journal,
                           String filename, boolean overwrite) throws Exception
    {
        if (overwrite || new File(filename).exists())
            try (PrintStream out = new PrintStream(filename)) {
                out.println(journal.toString());
            }
    }

    public void load(Journal journal, String filename) {}
    public void load(Journal journal, URL url) {}
}

class SRPDemo
{
    public static void main(String[] args) throws Exception
    {
        Journal j = new Journal();
        j.addEntry("I cried today");
        j.addEntry("I ate a bug");
        System.out.println(j);

        JournalPersistence p = new JournalPersistence();
        String filename = "C:\\tmp\\journal.txt";
        p.saveToFile(j, filename, true);

        // windows!
        Runtime.getRuntime().exec("notepad.exe " + filename);
    }
}