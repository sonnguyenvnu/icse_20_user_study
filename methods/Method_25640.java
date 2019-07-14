/** 
 * Allow return values to be ignored in tests that expect an exception to be thrown. 
 */
static boolean expectedExceptionTest(Tree tree,VisitorState state){
  if (mockitoInvocation(tree,state)) {
    return true;
  }
  StatementTree statement=ASTHelpers.findEnclosingNode(state.getPath(),StatementTree.class);
  if (statement != null && EXPECTED_EXCEPTION_MATCHER.matches(statement,state)) {
    return true;
  }
  return false;
}
