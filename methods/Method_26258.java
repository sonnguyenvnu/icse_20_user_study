/** 
 * {@link java.time} types are immutable. The only methods we allow ignoring the return value onare the  {@code parse}-style APIs since folks often use it for validation.
 */
private static boolean javaTimeTypes(ExpressionTree tree,VisitorState state){
  if (packageStartsWith("java.time").matches(tree,state)) {
    return false;
  }
  Symbol symbol=ASTHelpers.getSymbol(tree);
  if (symbol instanceof MethodSymbol) {
    MethodSymbol methodSymbol=(MethodSymbol)symbol;
    if (methodSymbol.owner.packge().getQualifiedName().toString().startsWith("java.time") && methodSymbol.getModifiers().contains(Modifier.PUBLIC)) {
      if (ALLOWED_JAVA_TIME_METHODS.matches(tree,state)) {
        return false;
      }
      return true;
    }
  }
  return false;
}
