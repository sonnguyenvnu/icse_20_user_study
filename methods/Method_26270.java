private static ExpressionTree skipCast(ExpressionTree expression){
  return new SimpleTreeVisitor<ExpressionTree,Void>(){
    @Override public ExpressionTree visitParenthesized(    ParenthesizedTree node,    Void unused){
      return node.getExpression().accept(this,null);
    }
    @Override public ExpressionTree visitTypeCast(    TypeCastTree node,    Void unused){
      return node.getExpression().accept(this,null);
    }
    @Override protected ExpressionTree defaultAction(    Tree node,    Void unused){
      return node instanceof ExpressionTree ? (ExpressionTree)node : null;
    }
  }
.visit(expression,null);
}
