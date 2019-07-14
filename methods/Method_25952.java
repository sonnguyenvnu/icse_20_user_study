private static boolean isGeneratedBaseType(ClassSymbol symbol,VisitorState state,String baseTypeName){
  Type baseType=state.getTypeFromString(baseTypeName);
  return ASTHelpers.isSubtype(symbol.asType(),baseType,state);
}
