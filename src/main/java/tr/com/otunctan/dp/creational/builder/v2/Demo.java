package tr.com.otunctan.dp.creational.builder.v2;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class HtmlElement {

    public String name, text;
    public List<HtmlElement> elements = new ArrayList<>();
    private final int indentSize = 2;
    private final String newLine = System.lineSeparator();

    public HtmlElement() {
    }

    public HtmlElement(String name, String text) {
        this.name = name;
        this.text = text;
    }

    private String toStringImpl(int indent) {
        StringBuilder sb = new StringBuilder();
        String i = String.join("", Collections.nCopies(indent * indentSize, " "));
        sb.append(String.format("%s<%s>%s", i, name, newLine));
        if (text != null && !text.isEmpty()) {
            sb.append(String.join("", Collections.nCopies(indentSize * (indent + 1), " ")))
                    .append(text)
                    .append(newLine);
        }

        for (HtmlElement element : elements) {
            sb.append(element.toStringImpl(indent + 1));
        }
        sb.append(String.format("%s</%s>%s", i, name, newLine));
        return sb.toString();

    }

    @Override
    public String toString() {
        return toStringImpl(0);
    }
}


class HtmlElementBuilder {
    private String rootName;
    private HtmlElement root = new HtmlElement();

    public HtmlElementBuilder(String rootName) {
        this.rootName = rootName;
        this.root.name = rootName;
    }

    public HtmlElementBuilder addChild(String childName, String childText) {
        HtmlElement e = new HtmlElement(childName, childText);
        root.elements.add(e);
        return this;
    }

    public void clear() {
        root = new HtmlElement();
        root.name = rootName;
    }

    @Override
    public String toString() {
        return root.toString();
    }
}

public class Demo {
    public static void main(String[] args) {
        HtmlElementBuilder builder = new HtmlElementBuilder("ul")//
        .addChild("li", "Java")//
        .addChild("li", "C#")//
        .addChild("li", "C++")//
        .addChild("li", "Angular");

        System.out.println(builder);


    }
}
