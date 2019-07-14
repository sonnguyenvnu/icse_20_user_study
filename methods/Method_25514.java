/** 
 * Gets the symbol for a method invocation. 
 */
@Nullable public static MethodSymbol getSymbol(MethodInvocationTree tree){
  Symbol sym=ASTHelpers.getSymbol(tree.getMethodSelect());
  if (!(sym instanceof MethodSymbol)) {
    return null;
  }
  return (MethodSymbol)sym;
}
