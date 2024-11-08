package tr.com.otunctan.dp.behavioural.visitor.v4;

public class DoubleExpression extends Expression {
    public double value;

    public DoubleExpression(double value) {
        this.value = value;
    }

    @Override
    public void accept(Visitor visitor) {
        if (visitor instanceof DoubleExpressionVisitor doubleExpressionVisitor) {
            doubleExpressionVisitor.visit(this);
        }
    }
}
