package tr.com.otunctan.dp.behavioural.visitor.v4;

public class AdditionExpression extends Expression {
    public Expression left, right;

    public AdditionExpression(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public void accept(Visitor visitor) {
        if (visitor instanceof AdditionExpressionVisitor additionExpressionVisitor) {
            additionExpressionVisitor.visit(this);
        }
    }
}
