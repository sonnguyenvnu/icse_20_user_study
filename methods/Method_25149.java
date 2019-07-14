/** 
 * Returns a new builder for  {@link Description}s.
 * @param tree the tree where the error is
 * @param checker the {@code BugChecker} instance that is producing this {@code Description}
 */
@CheckReturnValue public static Description.Builder buildDescriptionFromChecker(JCTree tree,BugChecker checker){
  return Description.builder((DiagnosticPosition)tree,checker.canonicalName(),checker.linkUrl(),checker.defaultSeverity(),checker.message());
}
