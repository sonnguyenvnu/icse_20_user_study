public boolean isOnLeftHandSide(){
  Node primaryExpression;
  if (location.jjtGetParent() instanceof ASTPrimaryExpression) {
    primaryExpression=location.jjtGetParent().jjtGetParent();
  }
 else   if (location.jjtGetParent().jjtGetParent() instanceof ASTPrimaryExpression) {
    primaryExpression=location.jjtGetParent().jjtGetParent().jjtGetParent();
  }
 else   if (location.jjtGetParent() instanceof ASTResource) {
    return false;
  }
 else {
    throw new RuntimeException("Found a NameOccurrence (" + location + ") that didn't have an ASTPrimary Expression" + " as parent or grandparent nor is a concise resource.  Parent = " + location.jjtGetParent() + " and grandparent = " + location.jjtGetParent().jjtGetParent() + " (location line " + location.getBeginLine() + " col " + location.getBeginColumn() + ")");
  }
  if (isStandAlonePostfix(primaryExpression)) {
    return true;
  }
  if (primaryExpression.jjtGetNumChildren() <= 1) {
    return false;
  }
  if (!(primaryExpression.jjtGetChild(1) instanceof ASTAssignmentOperator)) {
    return false;
  }
  if (isPartOfQualifiedName()) {
    return false;
  }
  return !isCompoundAssignment(primaryExpression);
}
