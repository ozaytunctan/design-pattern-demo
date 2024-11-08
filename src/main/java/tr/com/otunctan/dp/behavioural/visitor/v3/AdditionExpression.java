package tr.com.otunctan.dp.behavioural.visitor.v3;

public class AdditionExpression extends Expression{
    public Expression left, right;

    public AdditionExpression(Expression left, Expression right)
    {
        this.left = left;
        this.right = right;
    }

    @Override
    public void accept(ExpressionVisitor visitor)
    {
        visitor.visit(this);
    }


}
