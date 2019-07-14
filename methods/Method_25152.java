/** 
 * Returns true if the given tree is annotated with a  {@code @SuppressWarnings} that disables thisbug checker.
 */
public boolean isSuppressed(Tree tree){
  SuppressWarnings suppression=ASTHelpers.getAnnotation(tree,SuppressWarnings.class);
  return suppression != null && !Collections.disjoint(Arrays.asList(suppression.value()),allNames());
}
