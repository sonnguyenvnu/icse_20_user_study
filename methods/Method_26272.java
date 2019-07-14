/** 
 * We expect that the lhs is a field and the rhs is an identifier, specifically a parameter to the method. We base our suggested fixes on this expectation. <p>Case 1: If lhs is a field and rhs is an identifier, find a method parameter of the same type and similar name and suggest it as the rhs. (Guess that they have misspelled the identifier.) <p>Case 2: If lhs is a field and rhs is not an identifier, find a method parameter of the same type and similar name and suggest it as the rhs. <p>Case 3: If lhs is not a field and rhs is an identifier, find a class field of the same type and similar name and suggest it as the lhs. <p>Case 4: Otherwise suggest deleting the assignment.
 */
public Description describeForAssignment(AssignmentTree assignmentTree,VisitorState state){
  Tree parent=state.getPath().getParentPath().getLeaf();
  Fix fix=SuggestedFix.delete(parent);
  ExpressionTree lhs=assignmentTree.getVariable();
  ExpressionTree rhs=assignmentTree.getExpression();
  if (assignmentTree.getExpression().getKind() == METHOD_INVOCATION) {
    fix=SuggestedFix.replace(assignmentTree,state.getSourceForNode(rhs));
    rhs=stripNullCheck(rhs,state);
  }
  rhs=skipCast(rhs);
  ImmutableList<Fix> exploratoryFieldFixes=ImmutableList.of();
  if (lhs.getKind() == MEMBER_SELECT) {
    Preconditions.checkState(rhs.getKind() == IDENTIFIER || rhs.getKind() == MEMBER_SELECT);
    Type rhsType=ASTHelpers.getType(rhs);
    exploratoryFieldFixes=ReplacementVariableFinder.fixesByReplacingExpressionWithMethodParameter(rhs,varDecl -> ASTHelpers.isSameType(rhsType,varDecl.type,state),state);
  }
 else   if (rhs.getKind() == IDENTIFIER) {
    Preconditions.checkState(lhs.getKind() == IDENTIFIER);
    Type lhsType=ASTHelpers.getType(lhs);
    exploratoryFieldFixes=ReplacementVariableFinder.fixesByReplacingExpressionWithLocallyDeclaredField(lhs,var -> !Flags.isStatic(var.sym) && (var.sym.flags() & Flags.FINAL) == 0 && ASTHelpers.isSameType(lhsType,var.type,state),state);
  }
  if (exploratoryFieldFixes.isEmpty()) {
    return describeMatch(assignmentTree,fix);
  }
  return buildDescription(assignmentTree).addAllFixes(exploratoryFieldFixes).build();
}
