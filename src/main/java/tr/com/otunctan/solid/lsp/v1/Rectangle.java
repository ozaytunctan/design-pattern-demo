package tr.com.otunctan.solid.lsp.v1;

/**
 * Liskov substitution principle (LSP)
 *
 * Örnekte görüldüğü gibi kare bir diktörgen miras almasına rağmen diktörgenden kare olmadı. Bu açıkça LSP aykırı olduğunu öğrendik.
 *
 *
 */
public class Rectangle {
    protected int width, height;

    public Rectangle() {
    }

    public Rectangle(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getArea() {
        return width * height;
    }

    @Override
    public String toString() {
        return "Rectangle{" +
                "width=" + width +
                ", height=" + height +
                '}';
    }
}

//kare
class Square extends Rectangle {


    public Square() {
    }

    public Square(int size) {
        width = height = size;
    }

    @Override
    public void setWidth(int width) {
        super.setWidth(width);
        super.setHeight(width);
    }

    @Override
    public void setHeight(int height) {
        super.setWidth(height);
        super.setHeight(height);
    }
}

class Demo {

    static void useIt(Rectangle r) {
        int w = r.getWidth();
        r.setHeight(10);
        //area =width * 10;
        System.out.println("Expected area of :" + (w * 10) +
                ", got " + r.getArea()
        );
    }

    public static void main(String[] args) {

        useIt(new Rectangle(2,3));

        Square square = new Square();
        square.setWidth(5);
        useIt(square);

    }
}
