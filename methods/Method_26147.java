/** 
 * Desugars a compound assignment, making the cast explicit. 
 */
private static Optional<Fix> rewriteCompoundAssignment(CompoundAssignmentTree tree,VisitorState state){
  CharSequence var=state.getSourceForNode(tree.getVariable());
  CharSequence expr=state.getSourceForNode(tree.getExpression());
  if (var == null || expr == null) {
    return Optional.absent();
  }
switch (tree.getKind()) {
case RIGHT_SHIFT_ASSIGNMENT:
    return Optional.absent();
default :
  break;
}
Kind regularAssignmentKind=regularAssignmentFromCompound(tree.getKind());
String op=assignmentToString(regularAssignmentKind);
OperatorPrecedence rhsPrecedence=tree.getExpression() instanceof JCBinary ? OperatorPrecedence.from(tree.getExpression().getKind()) : tree.getExpression() instanceof ConditionalExpressionTree ? OperatorPrecedence.TERNARY : null;
if (rhsPrecedence != null) {
if (!rhsPrecedence.isHigher(OperatorPrecedence.from(regularAssignmentKind))) {
  expr=String.format("(%s)",expr);
}
}
String castType=getType(tree.getVariable()).toString();
String replacement=String.format("%s = (%s) (%s %s %s)",var,castType,var,op,expr);
return Optional.of(SuggestedFix.replace(tree,replacement));
}
