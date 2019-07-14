@Override public Description matchTry(TryTree tree,VisitorState state){
  if (tree.getCatches().isEmpty()) {
    return NO_MATCH;
  }
  ImmutableList<CatchTree> catchBlocks=tree.getCatches().stream().filter(c -> c.getBlock().getStatements().size() == 1 && FAIL_METHOD.matches(getOnlyElement(c.getBlock().getStatements()),state)).filter(c -> !catchVariableIsUsed(c)).collect(toImmutableList());
  if (catchBlocks.isEmpty()) {
    return NO_MATCH;
  }
  Description.Builder description=buildDescription(tree);
  rethrowFix(catchBlocks,state).ifPresent(description::addFix);
  deleteFix(tree,catchBlocks,state).ifPresent(description::addFix);
  return description.build();
}
