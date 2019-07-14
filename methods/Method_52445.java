/** 
 * Assert it the occurrence is a self assignment such as: <code>i += 3;</code>
 * @return true, if the occurrence is self-assignment, false, otherwise.
 */
@SuppressWarnings("PMD.AvoidBranchingStatementAsLastInLoop") public boolean isSelfAssignment(){
  Node l=location;
  while (true) {
    Node p=l.jjtGetParent();
    Node gp=p.jjtGetParent();
    Node node=gp.jjtGetParent();
    if (node instanceof ASTPreDecrementExpression || node instanceof ASTPreIncrementExpression || node instanceof ASTPostfixExpression) {
      return true;
    }
    if (hasAssignmentOperator(gp)) {
      return isCompoundAssignment(gp);
    }
    if (hasAssignmentOperator(node)) {
      return isCompoundAssignment(node);
    }
    if (p instanceof ASTPrimaryPrefix && p.jjtGetNumChildren() == 1 && gp instanceof ASTPrimaryExpression && gp.jjtGetNumChildren() == 1 && node instanceof ASTExpression && node.jjtGetNumChildren() == 1 && node.jjtGetParent() instanceof ASTPrimaryPrefix && node.jjtGetParent().jjtGetNumChildren() == 1) {
      l=node;
      continue;
    }
    return gp instanceof ASTPreDecrementExpression || gp instanceof ASTPreIncrementExpression || gp instanceof ASTPostfixExpression;
  }
}
