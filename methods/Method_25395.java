/** 
 * Determines whether an expression has an annotation with the given simple name. This does not include annotations inherited from superclasses due to @Inherited.
 * @param simpleName the simple name of the annotation (e.g. "Nullable")
 */
public static <T extends Tree>Matcher<T> hasAnnotationWithSimpleName(String simpleName){
  return (tree,state) -> ASTHelpers.hasDirectAnnotationWithSimpleName(ASTHelpers.getDeclaredSymbol(tree),simpleName);
}
