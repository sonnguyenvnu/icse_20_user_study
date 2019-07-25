/** 
 * Only checks the right operand of the container association operation because the left operand is either a literal or a pinned variable, which means the variable is being used and was declared elsewhere.
 */
private boolean execute(@NotNull final ElixirContainerAssociationOperation match,@NotNull ResolveState state){
  boolean keepProcessing=true;
  PsiElement[] children=match.getChildren();
  if (children.length > 1) {
    keepProcessing=execute(children[1],state);
  }
  return keepProcessing;
}
