@Override protected boolean matchArgument(ExpressionTree tree,VisitorState state){
  Type type=ASTHelpers.getType(tree);
  if (!type.isReference()) {
    return false;
  }
  ClassTree classTree=ASTHelpers.findEnclosingNode(state.getPath(),ClassTree.class);
  if (classTree == null) {
    return false;
  }
  Type classType=ASTHelpers.getType(classTree);
  if (classType == null) {
    return false;
  }
  if (inEqualsOrCompareTo(classType,type,state)) {
    return false;
  }
  if (ASTHelpers.isSubtype(type,state.getSymtab().enumSym.type,state)) {
    return false;
  }
  if (ASTHelpers.isSubtype(type,state.getSymtab().classType,state)) {
    return false;
  }
  if (!implementsEquals(type,state)) {
    return false;
  }
  return true;
}
