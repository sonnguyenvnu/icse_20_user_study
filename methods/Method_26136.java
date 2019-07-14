/** 
 * Check that invocations of methods annotated with  {@link MustBeClosed} are called within theresource variable initializer of a try-with-resources statement.
 */
@Override public Description matchMethodInvocation(MethodInvocationTree tree,VisitorState state){
  if (!HAS_MUST_BE_CLOSED_ANNOTATION.matches(tree,state)) {
    return NO_MATCH;
  }
  if (CONSTRUCTOR.matches(tree,state)) {
    return NO_MATCH;
  }
  return matchNewClassOrMethodInvocation(tree,state);
}
