/** 
 * Return the enclosing  {@code ClassSymbol} of the given symbol, or {@code null}. 
 */
public static ClassSymbol enclosingClass(Symbol sym){
  return sym.owner.enclClass();
}
