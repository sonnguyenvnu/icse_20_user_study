static Stream<String> constValues(Tree tree){
  if (tree instanceof ConditionalExpressionTree) {
    ConditionalExpressionTree cond=(ConditionalExpressionTree)tree;
    String t=ASTHelpers.constValue(cond.getTrueExpression(),String.class);
    String f=ASTHelpers.constValue(cond.getFalseExpression(),String.class);
    if (t == null || f == null) {
      return null;
    }
    return Stream.of(t,f);
  }
  String r=ASTHelpers.constValue(tree,String.class);
  return r != null ? Stream.of(r) : null;
}
