package tr.com.otunctan.dp.structural.bridge.exercise;

abstract class Shape
{
    public abstract String getName();
}

interface Renderer
{
    String whatToRenderAs();
}

public class RasterRenderer implements Renderer{
    @Override
    public String whatToRenderAs(){
        return "pixels";
    }
}

class VectorRenderer implements Renderer{
    @Override
    public String whatToRenderAs(){
        return "lines";
    }
}

class Triangle extends Shape
{

    private Renderer renderer;

    public Triangle(Renderer renderer){
        this.renderer = renderer;
    }

    @Override
    public String getName()
    {
        return "Triangle";
    }


    @Override
    public String toString()
    {
        return String.format("Drawing %s as %s", getName(), renderer.whatToRenderAs());
    }

}

class Square extends Shape
{

    private Renderer renderer;

    public Square(Renderer renderer){
        this.renderer = renderer;
    }


    @Override
    public String getName()
    {
        return "Square";
    }

    @Override
    public String toString()
    {
        return String.format("Drawing %s as %s", getName(), renderer.whatToRenderAs());
    }

}


class DemoBridge {

    public static void main(String[] args) {
        RasterRenderer rasterRenderer=new RasterRenderer();
        VectorRenderer vectorRenderer=new VectorRenderer();

        Triangle triangle = new Triangle(rasterRenderer);
        Square square=new Square(vectorRenderer);

        System.out.println(triangle);
        System.out.println(square);
    }
}



// imagine VectorTriangle and RasterTriangle are here too