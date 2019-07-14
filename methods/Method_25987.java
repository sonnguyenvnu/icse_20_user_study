@Override public Description matchVariable(VariableTree tree,VisitorState state){
  return check(ASTHelpers.getType(tree),tree.getInitializer());
}
