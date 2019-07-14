private static boolean isWhitelistedInterfaceMethod(MethodSymbol symbol,VisitorState state){
  return isWhitelistedInterfaceType(ASTHelpers.enclosingClass(symbol).type,state);
}
