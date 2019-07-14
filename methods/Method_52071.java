public boolean isCompileTimeConstant(ASTPrimaryExpression expressions){
  List<ASTPrimarySuffix> suffix=expressions.findDescendantsOfType(ASTPrimarySuffix.class);
  if (!suffix.isEmpty()) {
    return false;
  }
  List<ASTName> nameNodes=expressions.findDescendantsOfType(ASTName.class);
  List<ASTLiteral> literalNodes=expressions.findDescendantsOfType(ASTLiteral.class);
  if (nameNodes.size() + literalNodes.size() < 2) {
    for (    ASTName node : nameNodes) {
      if (!cache.contains(node.getImage())) {
        return false;
      }
    }
    return true;
  }
  List<ASTPrimaryExpression> subExpressions=expressions.findDescendantsOfType(ASTPrimaryExpression.class);
  for (  ASTPrimaryExpression exp : subExpressions) {
    if (!isCompileTimeConstant(exp)) {
      return false;
    }
  }
  return true;
}
