package tr.com.otunctan.dp.behavioural.visitor.v3;

public interface ExpressionVisitor {
    void visit(DoubleExpression e);
    void visit(AdditionExpression e);

}
