/** 
 * Returns true if the given symbol is annotated with a  {@code @SuppressWarnings} that disablesthis bug checker.
 */
public boolean isSuppressed(Symbol symbol){
  SuppressWarnings suppression=ASTHelpers.getAnnotation(symbol,SuppressWarnings.class);
  return suppression != null && !Collections.disjoint(Arrays.asList(suppression.value()),allNames());
}
