private boolean checkSetterStyleMethod(MethodInvocationTree tree,MethodSymbol symbol,VisitorState state){
  if (symbol.params().length() == 1 && ASTHelpers.isVoidType(symbol.getReturnType(),state) && tree.getArguments().size() == 1) {
    return check(symbol.name.toString(),tree.getArguments().get(0),state);
  }
  return false;
}
