/** 
 * Finds (if it exists) first (in the class hierarchy) non-interface super method of given  {@code method}.
 */
public static Optional<MethodSymbol> findSuperMethod(MethodSymbol methodSymbol,Types types){
  return findSuperMethods(methodSymbol,types,true).findFirst();
}
