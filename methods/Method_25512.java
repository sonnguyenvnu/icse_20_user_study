/** 
 * Gets the method symbol for a new class. 
 */
@Nullable public static MethodSymbol getSymbol(NewClassTree tree){
  Symbol sym=((JCNewClass)tree).constructor;
  return sym instanceof MethodSymbol ? (MethodSymbol)sym : null;
}
