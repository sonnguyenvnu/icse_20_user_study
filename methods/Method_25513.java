/** 
 * Gets the symbol for a variable. 
 */
public static VarSymbol getSymbol(VariableTree tree){
  return ((JCVariableDecl)tree).sym;
}
