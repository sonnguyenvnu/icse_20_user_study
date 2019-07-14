/** 
 * Walks up the receivers for an expression chain until one matching  {@code matcher} is found, andreturns the matched  {@link ExpressionTree}, or  {@code null} if none match.
 */
@Nullable private static ExpressionTree findReceiverMatching(ExpressionTree tree,VisitorState state,Matcher<ExpressionTree> matcher){
  while (tree instanceof MethodInvocationTree) {
    tree=ASTHelpers.getReceiver(tree);
    if (tree == null || matcher.matches(tree,state)) {
      return tree;
    }
  }
  return null;
}
