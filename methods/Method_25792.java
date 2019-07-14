/** 
 * Return a matcher for method invocations in which the method being called has the {@code @CheckReturnValue} annotation.
 */
@Override public Matcher<ExpressionTree> specializedMatcher(){
  return (tree,state) -> {
    Symbol sym=ASTHelpers.getSymbol(tree);
    if (!(sym instanceof MethodSymbol)) {
      return false;
    }
    MethodSymbol method=(MethodSymbol)sym;
    return shouldCheckReturnValue(method).orElseGet(() -> checkEnclosingClasses(method).orElseGet(() -> checkPackage(method).orElse(false)));
  }
;
}
