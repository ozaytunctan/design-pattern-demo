package tr.com.otunctan.dp.behavioural.visitor.v4;

public abstract class Expression {
    // optional
    public void accept(Visitor visitor) {
        if (visitor instanceof ExpressionVisitor) {
            ((ExpressionVisitor) visitor).visit(this);
        }
    }
}
