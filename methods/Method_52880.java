private boolean innerContainsSafeFields(final AbstractVFNode expression){
  for (int i=0; i < expression.jjtGetNumChildren(); i++) {
    Node child=expression.jjtGetChild(i);
    if (child instanceof ASTIdentifier) {
switch (child.getImage().toLowerCase(Locale.ROOT)) {
case "id":
case "size":
case "caseNumber":
        return true;
default :
    }
  }
  if (child instanceof ASTArguments) {
    if (containsSafeFields((ASTArguments)child)) {
      return true;
    }
  }
  if (child instanceof ASTDotExpression) {
    if (innerContainsSafeFields((ASTDotExpression)child)) {
      return true;
    }
  }
}
return false;
}
