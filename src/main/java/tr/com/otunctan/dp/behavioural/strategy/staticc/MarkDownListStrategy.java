package tr.com.otunctan.dp.behavioural.strategy.staticc;

public class MarkDownListStrategy implements ListStrategy {

    @Override
    public void addListItem(StringBuilder sb, String item) {
        sb.append(" * ")//
                .append(item)//
                .append(System.lineSeparator());

    }
}
