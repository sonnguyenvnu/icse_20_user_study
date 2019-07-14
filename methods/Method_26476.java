private static boolean canChangeMethodSignature(VisitorState state,MethodSymbol methodSymbol){
  return !ASTHelpers.methodCanBeOverridden(methodSymbol) && ASTHelpers.findSuperMethods(methodSymbol,state.getTypes()).isEmpty();
}
