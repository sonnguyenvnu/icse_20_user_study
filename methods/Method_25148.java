/** 
 * Returns a new builder for  {@link Description}s.
 * @param position the position of the error
 * @param checker the {@code BugChecker} instance that is producing this {@code Description}
 */
@CheckReturnValue public static Description.Builder buildDescriptionFromChecker(DiagnosticPosition position,BugChecker checker){
  return Description.builder(position,checker.canonicalName(),checker.linkUrl(),checker.defaultSeverity(),checker.message());
}
