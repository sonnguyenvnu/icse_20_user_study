private Description removeEqualsFromComparison(BinaryTree tree,VisitorState state,ExpressionType expressionType){
  String replacement=expressionType == ExpressionType.GREATER_THAN_EQUAL ? state.getSourceForNode(tree.getLeftOperand()) + " > 0" : "0 < " + state.getSourceForNode(tree.getRightOperand());
  return describeMatch(tree,SuggestedFix.replace(tree,replacement));
}
