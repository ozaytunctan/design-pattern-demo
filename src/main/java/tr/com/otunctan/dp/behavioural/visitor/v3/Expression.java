package tr.com.otunctan.dp.behavioural.visitor.v3;

public abstract class Expression {
    abstract void accept(ExpressionVisitor visitor);
}
