private static boolean returns(MethodSymbol symbol,String returnType,VisitorState state){
  return isSubtype(symbol.getReturnType(),state.getTypeFromString(returnType),state);
}
