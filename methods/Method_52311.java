@Override public Object visit(ASTVariableDeclaratorId node,Object data){
  ASTType typeNode=node.getTypeNode();
  if (typeNode == null || !TypeHelper.isA(typeNode,InputStream.class)) {
    return data;
  }
  for (  NameOccurrence occ : node.getUsages()) {
    JavaNameOccurrence jocc=(JavaNameOccurrence)occ;
    NameOccurrence qualifier=jocc.getNameForWhichThisIsAQualifier();
    if (qualifier != null && "skip".equals(qualifier.getImage())) {
      Node loc=jocc.getLocation();
      if (loc != null) {
        ASTPrimaryExpression exp=loc.getFirstParentOfType(ASTPrimaryExpression.class);
        while (exp != null) {
          if (exp.jjtGetParent() instanceof ASTStatementExpression) {
            addViolation(data,occ.getLocation());
            break;
          }
 else           if (exp.jjtGetParent() instanceof ASTExpression && exp.jjtGetParent().jjtGetParent() instanceof ASTPrimaryPrefix) {
            exp=exp.getFirstParentOfType(ASTPrimaryExpression.class);
          }
 else {
            break;
          }
        }
      }
    }
  }
  return data;
}
