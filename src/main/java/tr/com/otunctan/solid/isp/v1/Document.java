package tr.com.otunctan.solid.isp.v1;

/**
 * @author ozay.tunctan
 * <p>
 * Interface segregation Principle (ISp) arayüzlere ayırma prensibi
 */
public class Document {

}

interface Machine {
    void print(Document d);

    void fax(Document d);

    void scan(Document d);
}

class MultiFunctionPrinter implements Machine {

    @Override
    public void print(Document d) {

    }

    //    @Override
//    public void fax(Document d) throws IllegalAccessException {
//        throw new IllegalAccessException();
//    }
    @Override
    public void fax(Document d) {
        //bu method kullanılmadığı senaryoyu düşünün
    }

    @Override
    public void scan(Document d) {

    }
}

class OlFashionedPrinter implements Machine {

    @Override
    public void print(Document d) {

    }

    @Override
    public void fax(Document d) {

    }

    @Override
    public void scan(Document d) {

    }
}
