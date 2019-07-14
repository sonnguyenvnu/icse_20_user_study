private boolean ifVerify(ASTIfStatement is,String varname){
  List<ASTPrimaryExpression> finder=is.findDescendantsOfType(ASTPrimaryExpression.class);
  if (finder.size() > 1) {
    ASTPrimaryExpression nullStmt=findNonVariableStmt(varname,finder.get(0),finder.get(1));
    if (nullStmt != null) {
      if (nullStmt.jjtGetNumChildren() == 1 && nullStmt.jjtGetChild(0) instanceof ASTPrimaryPrefix) {
        ASTPrimaryPrefix pp2=(ASTPrimaryPrefix)nullStmt.jjtGetChild(0);
        if (pp2.jjtGetNumChildren() == 1 && pp2.jjtGetChild(0) instanceof ASTLiteral) {
          ASTLiteral lit=(ASTLiteral)pp2.jjtGetChild(0);
          if (lit.jjtGetNumChildren() == 1 && lit.jjtGetChild(0) instanceof ASTNullLiteral) {
            return true;
          }
        }
      }
    }
  }
  return false;
}
