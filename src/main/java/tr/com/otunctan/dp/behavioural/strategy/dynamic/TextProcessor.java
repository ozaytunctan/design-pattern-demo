package tr.com.otunctan.dp.behavioural.strategy.dynamic;

import java.util.List;

public class TextProcessor {

    private StringBuilder sb = new StringBuilder();
    private ListStrategy listStrategy;

    public TextProcessor(OutputFormat format) {
        setOutputFormat(format);
    }

    public void setOutputFormat(OutputFormat format) {
        this.listStrategy = switch (format) {
            case HTML -> new HtmlListStrategy();
            case MARKDOWN -> new MarkDownListStrategy();
        };
    }

    public void appendList(List<String> items) {
        listStrategy.start(sb);
        for (String item : items) {
            listStrategy.addListItem(sb, item);
        }
        listStrategy.end(sb);
    }

    public void clear() {
        sb.setLength(0);
    }

    @Override
    public String toString() {
        return sb.toString();
    }
}
