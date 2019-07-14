/** 
 * Extracts the variable from an AssignmentTree and applies a matcher to it. 
 */
private static Matcher<AssignmentTree> variableFromAssignmentTree(final Matcher<ExpressionTree> exprMatcher){
  return new Matcher<AssignmentTree>(){
    @Override public boolean matches(    AssignmentTree tree,    VisitorState state){
      return exprMatcher.matches(tree.getVariable(),state);
    }
  }
;
}
