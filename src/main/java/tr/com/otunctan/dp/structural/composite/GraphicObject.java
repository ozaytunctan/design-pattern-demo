package tr.com.otunctan.dp.structural.composite;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GraphicObject
{
    protected String name = "Group";

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public GraphicObject()
    {
    }

    public void addComponent(GraphicObject c){
        this.children.add(c);
    }
    public String color;
    public List<GraphicObject> children = new ArrayList<>();

    private void print(StringBuilder stringBuilder,  int depth)
    {
        stringBuilder.append(String.join("", Collections.nCopies(depth, "*")))
                .append(depth > 0 ? " " : "")
                .append((color == null || color.isEmpty()) ? "" : color + " ")
                .append(getName())
                .append(System.lineSeparator());
        for (GraphicObject child : children)
            child.print(stringBuilder,  depth+1);
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        print(sb, 0);
        return sb.toString();
    }
}

class Circle extends GraphicObject
{
    public Circle(String color)
    {
        name = "Circle";
        this.color = color;
    }
}

class Square extends GraphicObject
{
    public Square(String color)
    {
        name = "Square";
        this.color = color;
    }
}

class GeometricShapesDemo
{
    public static void main(String[] args)
    {
        GraphicObject drawing = new GraphicObject();
        drawing.setName("My Drawing");
        drawing.addComponent(new Square("Red"));
        drawing.addComponent(new Circle("Yellow"));

        GraphicObject group = new GraphicObject();
        group.addComponent(new Circle("Blue"));
        group.addComponent(new Square("Blue"));
        drawing.addComponent(group);

        System.out.println(drawing);
    }
}
