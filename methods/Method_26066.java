@Override public Description matchForLoop(ForLoopTree tree,VisitorState state){
  return checkCondition(tree.getCondition(),state);
}
