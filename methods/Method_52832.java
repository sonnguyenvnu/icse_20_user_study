public boolean isOnLeftHandSide(){
  Node primaryExpression;
  if (location.jjtGetParent() instanceof ASTPrimaryExpression) {
    primaryExpression=location.jjtGetParent().jjtGetParent();
  }
 else   if (location.jjtGetParent().jjtGetParent() instanceof ASTPrimaryExpression) {
    primaryExpression=location.jjtGetParent().jjtGetParent().jjtGetParent();
  }
 else {
    throw new RuntimeException("Found a NameOccurrence that didn't have an ASTPrimaryExpression as parent or grandparent. " + " Node = " + location.getClass().getCanonicalName() + ", Parent = " + location.jjtGetParent().getClass().getCanonicalName() + " and grandparent = " + location.jjtGetParent().jjtGetParent().getClass().getCanonicalName() + " @ line = " + location.getBeginLine() + ", column = " + location.getBeginColumn());
  }
  if (primaryExpression.jjtGetNumChildren() <= 1) {
    return false;
  }
  return !isPartOfQualifiedName();
}
