private static boolean hasFunctionAsArg(Tree param,VisitorState state){
  return ASTHelpers.isSameType(ASTHelpers.getType(param),state.getTypeFromString(JAVA_UTIL_FUNCTION_FUNCTION),state);
}
