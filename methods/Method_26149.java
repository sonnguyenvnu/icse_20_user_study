@Override public Description matchIf(IfTree ifTree,VisitorState visitorState){
  ExpressionTree expressionTree=stripParentheses(ifTree.getCondition());
  if (expressionTree instanceof InstanceOfTree) {
    InstanceOfTree instanceOfTree=(InstanceOfTree)expressionTree;
    if (!(instanceOfTree.getExpression() instanceof IdentifierTree)) {
      return Description.NO_MATCH;
    }
    Matcher<Tree> assignmentTreeMatcher=new AssignmentTreeMatcher(instanceOfTree.getExpression());
    Matcher<Tree> containsAssignmentTreeMatcher=contains(assignmentTreeMatcher);
    if (containsAssignmentTreeMatcher.matches(ifTree,visitorState)) {
      return Description.NO_MATCH;
    }
    Matcher<Tree> nestedInstanceOfMatcher=new NestedInstanceOfMatcher(instanceOfTree.getExpression(),instanceOfTree.getType());
    Matcher<Tree> containsNestedInstanceOfMatcher=contains(nestedInstanceOfMatcher);
    if (containsNestedInstanceOfMatcher.matches(ifTree.getThenStatement(),visitorState)) {
      return describeMatch(ifTree);
    }
  }
  return Description.NO_MATCH;
}
