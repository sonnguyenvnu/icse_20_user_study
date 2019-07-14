@Override public Choice<Unifier> visitEnhancedForLoop(EnhancedForLoopTree loop,Unifier unifier){
  return getVariable().unify(loop.getVariable(),unifier).thenChoose(unifications(getExpression(),loop.getExpression())).thenChoose(unifications(getStatement(),loop.getStatement()));
}
