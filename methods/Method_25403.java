/** 
 * Matches on the initializer of a VariableTree AST node.
 * @param expressionTreeMatcher A matcher on the initializer of the variable.
 */
public static Matcher<VariableTree> variableInitializer(Matcher<ExpressionTree> expressionTreeMatcher){
  return (variableTree,state) -> {
    ExpressionTree initializer=variableTree.getInitializer();
    return initializer != null && expressionTreeMatcher.matches(initializer,state);
  }
;
}
