/** 
 * Gets the symbol for a class. 
 */
public static ClassSymbol getSymbol(ClassTree tree){
  return ((JCClassDecl)tree).sym;
}
