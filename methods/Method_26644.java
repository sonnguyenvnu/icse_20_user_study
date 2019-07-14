@Override @Nullable public Choice<Unifier> visitConditionalExpression(ConditionalExpressionTree conditional,Unifier unifier){
  return getCondition().unify(conditional.getCondition(),unifier.fork()).thenChoose(unifications(getTrueExpression(),conditional.getTrueExpression())).thenChoose(unifications(getFalseExpression(),conditional.getFalseExpression())).or(getCondition().negate().unify(conditional.getCondition(),unifier.fork()).thenChoose(unifications(getFalseExpression(),conditional.getTrueExpression())).thenChoose(unifications(getTrueExpression(),conditional.getFalseExpression())));
}
