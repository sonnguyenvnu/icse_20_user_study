@Override @Nullable public Choice<Unifier> visitForLoop(ForLoopTree loop,@Nullable Unifier unifier){
  return UBlock.unifyStatementList(getInitializer(),loop.getInitializer(),unifier).thenChoose(unifications(getCondition(),loop.getCondition())).thenChoose(unifications(getUpdate(),loop.getUpdate())).thenChoose(unifications(getStatement(),loop.getStatement()));
}
