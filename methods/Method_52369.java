@Override public Object visit(ASTMethodDeclaration node,Object data){
  if (checkNonStaticMethods && !node.isStatic() || node.isSynchronized()) {
    return super.visit(node,data);
  }
  List<ASTIfStatement> ifStatements=node.findDescendantsOfType(ASTIfStatement.class);
  for (  ASTIfStatement ifStatement : ifStatements) {
    if (ifStatement.getFirstParentOfType(ASTSynchronizedStatement.class) == null) {
      if (!ifStatement.hasDescendantOfType(ASTNullLiteral.class)) {
        continue;
      }
      ASTName n=ifStatement.getFirstDescendantOfType(ASTName.class);
      if (n == null || !fieldDecls.containsKey(n.getImage())) {
        continue;
      }
      List<ASTAssignmentOperator> assigmnents=ifStatement.findDescendantsOfType(ASTAssignmentOperator.class);
      boolean violation=false;
      for (int ix=0; ix < assigmnents.size(); ix++) {
        ASTAssignmentOperator oper=assigmnents.get(ix);
        if (!(oper.jjtGetParent() instanceof ASTStatementExpression)) {
          continue;
        }
        ASTStatementExpression expr=(ASTStatementExpression)oper.jjtGetParent();
        if (expr.jjtGetChild(0) instanceof ASTPrimaryExpression && ((ASTPrimaryExpression)expr.jjtGetChild(0)).jjtGetNumChildren() == 1 && ((ASTPrimaryExpression)expr.jjtGetChild(0)).jjtGetChild(0) instanceof ASTPrimaryPrefix) {
          ASTPrimaryPrefix pp=(ASTPrimaryPrefix)((ASTPrimaryExpression)expr.jjtGetChild(0)).jjtGetChild(0);
          String name=null;
          if (pp.usesThisModifier()) {
            ASTPrimarySuffix priSuf=expr.getFirstDescendantOfType(ASTPrimarySuffix.class);
            name=priSuf.getImage();
          }
 else {
            ASTName astName=(ASTName)pp.jjtGetChild(0);
            name=astName.getImage();
          }
          if (fieldDecls.containsKey(name)) {
            violation=true;
          }
        }
      }
      if (violation) {
        addViolation(data,ifStatement);
      }
    }
  }
  return super.visit(node,data);
}
