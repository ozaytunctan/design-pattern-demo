package tr.com.otunctan.dp.behavioural.strategy.dynamic;

import java.util.List;

public class Demo {

    public static void main(String[] args) {

        TextProcessor tp = new TextProcessor(OutputFormat.MARKDOWN);
        tp.appendList(List.of("ozay","eylül","ada","yağmur"));
        System.out.println(tp);

        tp.clear();
        tp.setOutputFormat(OutputFormat.HTML);
        tp.appendList(List.of("inheritance","encapsulation", "polymorphism"));
        System.out.println(tp);

    }
}
