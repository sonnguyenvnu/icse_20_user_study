/** 
 * Check to see if the variable should be considered for replacement, i.e. <ul> <li>A variable without an initializer <li>Enhanced for loop variables can be replaced if they are loops over primitive arrays <li>A variable initialized with a primitive value (which is then auto-boxed) <li>A variable initialized with an invocation of  {@code Boxed.valueOf}, since that can be replaced with  {@code Boxed.parseBoxed}. </ul>
 */
private static boolean variableMatches(VariableTree tree,VisitorState state){
  ExpressionTree expression=tree.getInitializer();
  if (expression == null) {
    Tree leaf=state.getPath().getParentPath().getLeaf();
    if (!(leaf instanceof EnhancedForLoopTree)) {
      return true;
    }
    EnhancedForLoopTree node=(EnhancedForLoopTree)leaf;
    Type expressionType=ASTHelpers.getType(node.getExpression());
    if (expressionType == null) {
      return false;
    }
    Type elemtype=state.getTypes().elemtype(expressionType);
    return elemtype != null && elemtype.isPrimitive();
  }
  Type initializerType=ASTHelpers.getType(expression);
  if (initializerType == null) {
    return false;
  }
  if (initializerType.isPrimitive()) {
    return true;
  }
  return VALUE_OF_MATCHER.matches(expression,state);
}
