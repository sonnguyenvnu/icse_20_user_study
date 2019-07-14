/** 
 * Gets the symbol for a method. 
 */
public static MethodSymbol getSymbol(MethodTree tree){
  return ((JCMethodDecl)tree).sym;
}
