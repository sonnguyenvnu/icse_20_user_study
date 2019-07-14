/** 
 * Check that the  {@link MustBeClosed} annotation is only used for constructors of AutoCloseablesand methods that return an AutoCloseable.
 */
@Override public Description matchMethod(MethodTree tree,VisitorState state){
  if (!HAS_MUST_BE_CLOSED_ANNOTATION.matches(tree,state)) {
    return NO_MATCH;
  }
  boolean isAConstructor=methodIsConstructor().matches(tree,state);
  if (isAConstructor && !AUTO_CLOSEABLE_CONSTRUCTOR_MATCHER.matches(tree,state)) {
    return buildDescription(tree).setMessage("MustBeClosed should only annotate constructors of AutoCloseables.").build();
  }
  if (!isAConstructor && !METHOD_RETURNS_AUTO_CLOSEABLE_MATCHER.matches(tree,state)) {
    return buildDescription(tree).setMessage("MustBeClosed should only annotate methods that return an AutoCloseable.").build();
  }
  return NO_MATCH;
}
