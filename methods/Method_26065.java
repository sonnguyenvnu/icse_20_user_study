@Override public Description matchIf(IfTree tree,VisitorState state){
  return checkCondition(skipOneParen(tree.getCondition()),state);
}
