package tr.com.otunctan.dp.behavioural.strategy.staticc;

import java.util.List;

public class Demo {

    public static void main(String[] args) {

        TextProcessor<MarkDownListStrategy>tp=new TextProcessor<>(
                MarkDownListStrategy::new
        );
        tp.appendList(List.of("Alpha","Beta","Gamma"));
        System.out.println(tp);


        TextProcessor<HtmlListStrategy>htp=new TextProcessor<>(
                HtmlListStrategy::new
        );
        htp.appendList(List.of("Java","C#","C++"));
        System.out.println(htp);




    }
}
