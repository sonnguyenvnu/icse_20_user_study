@Override protected boolean matchArgument(ExpressionTree tree,VisitorState state){
  Type type=ASTHelpers.getType(tree);
  for (  String className : OPTIONAL_CLASSES) {
    if (ASTHelpers.isSameType(type,state.getTypeFromString(className),state)) {
      return true;
    }
  }
  return false;
}
