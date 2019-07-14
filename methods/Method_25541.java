/** 
 * Check for the presence of an annotation with a specific simple name directly on this symbol. Does *not* consider annotation inheritance.
 * @param tree the tree to check for the presence of the annotation
 * @param simpleName the simple name of the annotation to look for, e.g. "Nullable" or"CheckReturnValue"
 */
public static boolean hasDirectAnnotationWithSimpleName(Tree tree,String simpleName){
  return hasDirectAnnotationWithSimpleName(getDeclaredSymbol(tree),simpleName);
}
