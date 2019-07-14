@Override Type extractSourceType(MethodInvocationTree tree,VisitorState state){
  return extractTypeArgAsMemberOfSupertype(ASTHelpers.getType(Iterables.get(tree.getArguments(),methodArgIndex)),state.getSymbolFromString(methodArgTypeName),methodArgTypeArgIndex,state.getTypes());
}
