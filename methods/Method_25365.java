/** 
 * Checks if a method, or any overridden method, is annotated with any annotation from the org.junit package.
 */
public static boolean hasJUnitAnnotation(MethodTree tree,VisitorState state){
  MethodSymbol methodSym=getSymbol(tree);
  if (methodSym == null) {
    return false;
  }
  if (hasJUnitAttr(methodSym)) {
    return true;
  }
  return findSuperMethods(methodSym,state.getTypes()).stream().anyMatch(JUnitMatchers::hasJUnitAttr);
}
