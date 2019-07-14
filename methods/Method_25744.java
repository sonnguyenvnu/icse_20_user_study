@Override public Description matchTypeCast(TypeCastTree tree,VisitorState state){
  if (!matches(tree,state)) {
    return Description.NO_MATCH;
  }
  ClassTree declaringClass=ASTHelpers.findEnclosingNode(state.getPath(),ClassTree.class);
  if (!COMPARABLE_CLASS_MATCHER.matches(declaringClass,state) && !COMPARATOR_CLASS_MATCHER.matches(declaringClass,state)) {
    return Description.NO_MATCH;
  }
  MethodTree method=ASTHelpers.findEnclosingNode(state.getPath(),MethodTree.class);
  if (method == null) {
    return Description.NO_MATCH;
  }
  if (!COMPARABLE_METHOD_MATCHER.matches(method,state) && !COMPARATOR_METHOD_MATCHER.matches(method,state)) {
    return Description.NO_MATCH;
  }
  BinaryTree subtract=(BinaryTree)ASTHelpers.stripParentheses(tree.getExpression());
  ExpressionTree lhs=subtract.getLeftOperand();
  ExpressionTree rhs=subtract.getRightOperand();
  Fix fix;
  if (ASTHelpers.getType(lhs).isPrimitive()) {
    fix=SuggestedFix.replace(tree,"Long.compare(" + state.getSourceForNode(lhs) + ", " + state.getSourceForNode(rhs) + ")");
  }
 else {
    fix=SuggestedFix.replace(tree,state.getSourceForNode(lhs) + ".compareTo(" + state.getSourceForNode(rhs) + ")");
  }
  return describeMatch(tree,fix);
}
