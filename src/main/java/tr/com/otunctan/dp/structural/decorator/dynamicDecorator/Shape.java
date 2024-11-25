package tr.com.otunctan.dp.structural.decorator.dynamicDecorator;

public interface Shape {

    String info();
}

class Circle implements Shape {
    private float radius;

    public Circle() {
    }

    public Circle(float radius) {
        this.radius = radius;
    }

    @Override
    public String info() {
        return "A circle of radius " + radius;
    }

    void resize(float factor) {
        this.radius *= factor;
    }
}

class Square implements Shape {

    private float side;

    public Square() {
    }

    public Square(float side) {
        this.side = side;
    }

    @Override
    public String info() {
        return "A square with side " + side;
    }

}

// we are NOT altering the base class of these objects
// cannot make ColoredSquare, ColoredCircle

class ColoredShape implements Shape {

    private Shape shape;
    private String color;

    public ColoredShape(Shape shape, String color) {
        this.shape = shape;
        this.color = color;
    }

    @Override
    public String info() {
        return shape.info() + " has the color " + color;
    }

}

class TransparentShape implements Shape {
    private Shape shape;
    private int transparency;

    public TransparentShape(Shape shape, int transparency) {
        this.shape = shape;
        this.transparency = transparency;
    }

    @Override
    public String info() {
        return shape.info() + " has " + transparency + "% transparency";
    }
}


class DynamicDecoratorDemo {

    public static void main(String[] args) {

        Circle circle = new Circle(10);
        System.out.println(circle.info());

        Square square = new Square(5);
        System.out.println(square.info());


        ColoredShape redSquare = new ColoredShape(new Square(20), "red");
        System.out.println(redSquare.info());

        TransparentShape myCircle = new TransparentShape(
                new ColoredShape(new Circle(4), "green"),
                50
        );

        System.out.println(myCircle.info());

    }
}