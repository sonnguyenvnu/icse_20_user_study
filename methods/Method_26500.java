private boolean sideEffectFreeConstructor(TypeSymbol classType,VisitorState state){
  if (classType.isInterface()) {
    return true;
  }
  for (  String typeName : TYPE_WHITELIST) {
    if (ASTHelpers.isSameType(classType.type,state.getTypeFromString(typeName),state)) {
      return true;
    }
  }
  return false;
}
