/** 
 * Method visit.
 * @param node ASTVariableDeclaratorId
 * @param data Object
 * @return Object
 * @see net.sourceforge.pmd.lang.java.ast.JavaParserVisitor#visit(ASTVariableDeclaratorId,Object)
 */
@Override public Object visit(ASTVariableDeclaratorId node,Object data){
  if (!targetTypename().equals(node.getNameDeclaration().getTypeImage())) {
    return data;
  }
  for (  NameOccurrence occ : node.getUsages()) {
    JavaNameOccurrence jocc=(JavaNameOccurrence)occ;
    if (isNotedMethod(jocc.getNameForWhichThisIsAQualifier())) {
      Node parent=jocc.getLocation().jjtGetParent().jjtGetParent();
      if (parent instanceof ASTPrimaryExpression) {
        if (parent.hasDescendantOfType(ASTAdditiveExpression.class)) {
          return data;
        }
        List<ASTLiteral> literals=parent.findDescendantsOfType(ASTLiteral.class);
        for (int l=0; l < literals.size(); l++) {
          ASTLiteral literal=literals.get(l);
          if (isViolationArgument(literal)) {
            addViolation(data,jocc.getLocation());
          }
        }
      }
    }
  }
  return data;
}
