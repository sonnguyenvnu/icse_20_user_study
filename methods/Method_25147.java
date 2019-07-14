/** 
 * Returns a new builder for  {@link Description}s.
 * @param node the node where the error is
 * @param checker the {@code BugChecker} instance that is producing this {@code Description}
 */
@CheckReturnValue public static Description.Builder buildDescriptionFromChecker(Tree node,BugChecker checker){
  return Description.builder(Preconditions.checkNotNull(node),checker.canonicalName(),checker.linkUrl(),checker.defaultSeverity(),checker.message());
}
