@Override public Description matchWhileLoop(WhileLoopTree tree,VisitorState state){
  return checkCondition(skipOneParen(tree.getCondition()),state);
}
