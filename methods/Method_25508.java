/** 
 * Gets the symbol declared by a tree. Returns null if  {@code tree} does not declare a symbol oris null.
 */
@Nullable public static Symbol getDeclaredSymbol(Tree tree){
  if (tree instanceof AnnotationTree) {
    return getSymbol(((AnnotationTree)tree).getAnnotationType());
  }
  if (tree instanceof PackageTree) {
    return getSymbol((PackageTree)tree);
  }
  if (tree instanceof TypeParameterTree) {
    Type type=((JCTypeParameter)tree).type;
    return type == null ? null : type.tsym;
  }
  if (tree instanceof ClassTree) {
    return getSymbol((ClassTree)tree);
  }
  if (tree instanceof MethodTree) {
    return getSymbol((MethodTree)tree);
  }
  if (tree instanceof VariableTree) {
    return getSymbol((VariableTree)tree);
  }
  return null;
}
