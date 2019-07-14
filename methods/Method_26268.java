@Override public Description matchAssignment(AssignmentTree tree,VisitorState state){
  ExpressionTree expression=stripNullCheck(tree.getExpression(),state);
  expression=skipCast(expression);
  if (ASTHelpers.sameVariable(tree.getVariable(),expression)) {
    return describeForAssignment(tree,state);
  }
  return Description.NO_MATCH;
}
