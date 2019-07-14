@Override public Description matchWhileLoop(WhileLoopTree tree,VisitorState state){
  return check(tree.getCondition(),ImmutableList.of(tree.getCondition(),tree.getStatement()));
}
