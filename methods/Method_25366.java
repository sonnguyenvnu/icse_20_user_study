/** 
 * Checks if a method symbol has any attribute from the org.junit package. 
 */
private static boolean hasJUnitAttr(MethodSymbol methodSym){
  return methodSym.getRawAttributes().stream().anyMatch(attr -> attr.type.tsym.getQualifiedName().toString().startsWith("org.junit."));
}
