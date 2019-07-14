/** 
 * Extracts the expression from a UnaryTree and applies a matcher to it. 
 */
private static Matcher<UnaryTree> expressionFromUnaryTree(final Matcher<ExpressionTree> exprMatcher){
  return new Matcher<UnaryTree>(){
    @Override public boolean matches(    UnaryTree tree,    VisitorState state){
      return exprMatcher.matches(tree.getExpression(),state);
    }
  }
;
}
