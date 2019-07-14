@Override protected boolean matchArgument(ExpressionTree tree,VisitorState state){
  return ASTHelpers.isSameType(ASTHelpers.getType(tree),state.getSymtab().stringType,state);
}
