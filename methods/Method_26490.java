static Matcher<ExpressionTree> sourceMatcher(String source){
  return (tree,state) -> state.getSourceForNode(tree).equals(source);
}
