private static ExpressionTree skipOneParen(ExpressionTree tree){
  return tree instanceof ParenthesizedTree ? ((ParenthesizedTree)tree).getExpression() : tree;
}
