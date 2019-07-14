@Override @Nullable public Choice<Unifier> visitUnary(UnaryTree unary,@Nullable Unifier unifier){
  return Choice.condition(getKind().equals(unary.getKind()),unifier).thenChoose(unifications(getExpression(),ASTHelpers.stripParentheses(unary.getExpression())));
}
