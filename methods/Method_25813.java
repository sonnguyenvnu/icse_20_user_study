@Override Type extractTargetType(MethodInvocationTree tree,VisitorState state){
  return extractTypeArgAsMemberOfSupertype(ASTHelpers.getReceiverType(tree),state.getSymbolFromString(receiverTypeName),receiverTypeArgIndex,state.getTypes());
}
