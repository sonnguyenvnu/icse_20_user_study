/** 
 * Check that construction of constructors annotated with  {@link MustBeClosed} occurs within theresource variable initializer of a try-with-resources statement.
 */
@Override public Description matchNewClass(NewClassTree tree,VisitorState state){
  if (!HAS_MUST_BE_CLOSED_ANNOTATION.matches(tree,state)) {
    return NO_MATCH;
  }
  return matchNewClassOrMethodInvocation(tree,state);
}
