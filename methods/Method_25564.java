/** 
 * Finds the end position of this MethodInvocationTree. This is complicated by the fact that sometimes a comment will fall outside of the bounds of the tree. <p>For example: <pre> test(arg1,  // comment1 arg2); // comment2 int i; </pre> In this case  {@code comment2} lies beyond the end of the invocation tree. In order to get thecomment we need the tokenizer to lex the token which follows the invocation ( {@code int} in theexample). <p>We over-approximate this end position by looking for the next node in the AST and using the end position of this node. <p>As a heuristic we first scan for any  {@code /} characters on the same line as the end of themethod invocation. If we don't find any then we use the end of the method invocation as the end position.
 * @return the end position of the tree or Optional.empty if we are unable to calculate it
 */
@VisibleForTesting static Optional<Integer> computeEndPosition(Tree methodInvocationTree,CharSequence sourceCode,VisitorState state){
  int invocationEnd=state.getEndPosition(methodInvocationTree);
  if (invocationEnd == -1) {
    return Optional.empty();
  }
  int nextNewLine=CharMatcher.is('\n').indexIn(sourceCode,invocationEnd);
  if (nextNewLine == -1) {
    return Optional.of(invocationEnd);
  }
  if (CharMatcher.is('/').matchesNoneOf(sourceCode.subSequence(invocationEnd,nextNewLine))) {
    return Optional.of(invocationEnd);
  }
  int nextNodeEnd=state.getEndPosition(getNextNodeOrParent(methodInvocationTree,state));
  if (nextNodeEnd == -1) {
    return Optional.of(invocationEnd);
  }
  return Optional.of(nextNodeEnd);
}
