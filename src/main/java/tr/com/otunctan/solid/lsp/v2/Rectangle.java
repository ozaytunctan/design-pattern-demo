package tr.com.otunctan.solid.lsp.v2;


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

    public boolean isSquare() {
        return width == height;
    }
}

//kare
class Square extends Rectangle {


    public Square() {
    }

    public Square(int side) {
        width = height = side;
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


class RectangleFactory {

    public static Rectangle createRectangle(int width, int height) {
        return new Rectangle(width, height);
    }

    public static Rectangle createSquare(int side) {
        return new Rectangle(side, side);
    }


}

class Demo {

    public static void main(String[] args) {

        Rectangle rectangle = RectangleFactory.createRectangle(2, 3);
        Rectangle square = RectangleFactory.createSquare(3);
        System.out.println(square.isSquare());

    }
}
