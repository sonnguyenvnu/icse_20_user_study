@Override public Description matchSynchronized(SynchronizedTree tree,VisitorState state){
  Symbol lock=ASTHelpers.getSymbol(stripParentheses(tree.getExpression()));
  if (!(lock instanceof VarSymbol)) {
    return Description.NO_MATCH;
  }
  if (lock.isStatic()) {
    return Description.NO_MATCH;
  }
  Multimap<VarSymbol,Tree> writes=WriteVisitor.scan(tree.getBlock());
  for (  Entry<VarSymbol,Tree> write : writes.entries()) {
    if (!write.getKey().isStatic()) {
      continue;
    }
    state.reportMatch(buildDescription(write.getValue()).setMessage(String.format(MESSAGE,lock)).build());
  }
  return Description.NO_MATCH;
}
