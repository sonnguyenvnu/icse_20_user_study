/** 
 * Extracts the variable from a CompoundAssignmentTree and applies a matcher to it. 
 */
private static Matcher<CompoundAssignmentTree> variableFromCompoundAssignmentTree(final Matcher<ExpressionTree> exprMatcher){
  return new Matcher<CompoundAssignmentTree>(){
    @Override public boolean matches(    CompoundAssignmentTree tree,    VisitorState state){
      return exprMatcher.matches(tree.getVariable(),state);
    }
  }
;
}
