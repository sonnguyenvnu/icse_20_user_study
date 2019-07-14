/** 
 * Allow creating obsolete types when overriding a method with an obsolete return type. 
 */
private static boolean implementingObsoleteMethod(MethodTree enclosingMethod,VisitorState state,Type type){
  MethodSymbol method=ASTHelpers.getSymbol(enclosingMethod);
  if (method == null) {
    return false;
  }
  if (ASTHelpers.findSuperMethods(method,state.getTypes()).isEmpty()) {
    return false;
  }
  if (!ASTHelpers.isSameType(method.getReturnType(),type,state)) {
    return false;
  }
  return true;
}
