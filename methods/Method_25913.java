/** 
 * Returning a type of Future from a lambda or method that returns Object loses the Future type, which can result in suppressed errors or race conditions.
 */
@Override public Description matchReturn(ReturnTree tree,VisitorState state){
  Type objectType=state.getTypeFromString("java.lang.Object");
  Type futureType=state.getTypeFromString("java.util.concurrent.Future");
  if (futureType == null) {
    return Description.NO_MATCH;
  }
  Type resultType=ASTHelpers.getResultType(tree.getExpression());
  if (resultType == null) {
    return Description.NO_MATCH;
  }
  if (resultType.getKind() == TypeKind.NULL || resultType.getKind() == TypeKind.NONE) {
    return Description.NO_MATCH;
  }
  if (ASTHelpers.isSubtype(resultType,futureType,state)) {
    for (    Tree enclosing : state.getPath()) {
      if (enclosing instanceof MethodTree) {
        MethodTree methodTree=(MethodTree)enclosing;
        MethodSymbol symbol=ASTHelpers.getSymbol(methodTree);
        if (ASTHelpers.isSubtype(objectType,symbol.getReturnType(),state) && !isWhitelistedInterfaceMethod(symbol,state)) {
          return buildDescription(tree).setMessage(String.format("Returning %s from method that returns %s. Errors from the returned future" + " may be ignored.",resultType,symbol.getReturnType())).build();
        }
 else {
          break;
        }
      }
      if (enclosing instanceof LambdaExpressionTree) {
        LambdaExpressionTree lambdaTree=(LambdaExpressionTree)enclosing;
        if (isObjectReturningLambdaExpression(lambdaTree,state)) {
          return buildDescription(tree).setMessage(String.format("Returning %s from method that returns Object. Errors from the returned" + " future will be ignored.",resultType)).build();
        }
 else {
          break;
        }
      }
    }
  }
  return Description.NO_MATCH;
}
