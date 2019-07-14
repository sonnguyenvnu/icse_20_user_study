/** 
 * Determines whether two  {@link ExpressionTree} instances are equal. Only handles the casesrelevant to this checker: array accesses, identifiers, and literals. Returns false for all other cases.
 */
private static boolean expressionsEqual(ExpressionTree expr1,ExpressionTree expr2){
  if (!expr1.getKind().equals(expr2.getKind())) {
    return false;
  }
  if (!expr1.getKind().equals(Kind.ARRAY_ACCESS) && !expr1.getKind().equals(Kind.IDENTIFIER) && !(expr1 instanceof LiteralTree)) {
    return false;
  }
  if (expr1.getKind() == Kind.ARRAY_ACCESS) {
    ArrayAccessTree arrayAccessTree1=(ArrayAccessTree)expr1;
    ArrayAccessTree arrayAccessTree2=(ArrayAccessTree)expr2;
    return expressionsEqual(arrayAccessTree1.getExpression(),arrayAccessTree2.getExpression()) && expressionsEqual(arrayAccessTree1.getIndex(),arrayAccessTree2.getIndex());
  }
  if (expr1 instanceof LiteralTree) {
    LiteralTree literalTree1=(LiteralTree)expr1;
    LiteralTree literalTree2=(LiteralTree)expr2;
    return literalTree1.getValue().equals(literalTree2.getValue());
  }
  return Objects.equal(ASTHelpers.getSymbol(expr1),ASTHelpers.getSymbol(expr2));
}
