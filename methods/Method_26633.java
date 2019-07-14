@Override @Nullable public Choice<Unifier> visitCatch(CatchTree node,@Nullable Unifier unifier){
  return getParameter().unify(node.getParameter(),unifier).thenChoose(unifications(getBlock(),node.getBlock()));
}
