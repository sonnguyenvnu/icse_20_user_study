@Override @Nullable public Choice<Unifier> visitDoWhileLoop(DoWhileLoopTree loop,@Nullable Unifier unifier){
  return getStatement().unify(loop.getStatement(),unifier).thenChoose(unifications(getCondition(),loop.getCondition()));
}
