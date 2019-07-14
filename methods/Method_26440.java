private static Matcher<ExpressionTree> hasReceiverMatching(Matcher<ExpressionTree> matcher){
  return (tree,state) -> findReceiverMatching(tree,state,matcher) != null;
}
