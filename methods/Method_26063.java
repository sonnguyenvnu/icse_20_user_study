@Override public Description matchMethodInvocation(MethodInvocationTree tree,VisitorState state){
  if (!LOCK.matches(tree,state)) {
    return NO_MATCH;
  }
  Tree parent=state.getPath().getParentPath().getLeaf();
  if (!(parent instanceof StatementTree)) {
    return NO_MATCH;
  }
  Tree enclosing=state.getPath().getParentPath().getParentPath().getLeaf();
  if (!(enclosing instanceof BlockTree)) {
    return NO_MATCH;
  }
  BlockTree block=(BlockTree)enclosing;
  int index=block.getStatements().indexOf(parent);
  if (index + 1 < block.getStatements().size()) {
    StatementTree nextStatement=block.getStatements().get(index + 1);
    if (nextStatement instanceof TryTree) {
      return NO_MATCH;
    }
  }
  return describe(tree,state.getPath().getParentPath(),state);
}
