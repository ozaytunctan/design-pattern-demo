package tr.com.otunctan.solid.isp.v2;

public class Document {

}


interface Printer {
    void print(Document document);
}

interface Fax {
    void fax(Document document);
}

interface Scanner {
    void scan(Document document);
}


// tek yapmamız gereken print etmek =YAGNI = You Ain't to Need It
class JustAPrinter implements Printer {

    @Override
    public void print(Document document) {
        System.out.println("JustAPrinter document printing...");
    }
}

class Photocopier implements Printer, Scanner {
    @Override
    public void print(Document document) {
        System.out.println("Photocopier document printing...");
    }

    @Override
    public void scan(Document document) {
        System.out.println("Photocopier document scanning...");
    }
}

interface MultiFunctionDevice extends Printer, Scanner {

}

class MultiFunctionMachine implements MultiFunctionDevice {

    private Printer printer;
    private Scanner scanner;

    public MultiFunctionMachine(Printer printer, Scanner scanner) {
        this.printer = printer;
        this.scanner = scanner;
    }

    @Override
    public void print(Document document) {
        printer.print(document);
    }

    @Override
    public void scan(Document document) {
        scanner.scan(document);
    }
}

class Demo {
    public static void main(String[] args) {

        Document document = new Document();
        Printer justAPrinter = new JustAPrinter();
        justAPrinter.print(document);

        System.out.println("----");

        Scanner photocopier = new Photocopier();
        photocopier.scan(document);

        System.out.println("----");

        MultiFunctionDevice multiFunctionDevice = new MultiFunctionMachine(justAPrinter, photocopier);
        multiFunctionDevice.print(document);
        multiFunctionDevice.scan(document);


    }
}
