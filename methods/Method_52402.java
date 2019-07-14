@Override public Object visit(ASTFieldDeclaration fieldDeclaration,Object data){
  if (fieldDeclaration.isFinal()) {
    return data;
  }
  for (  ASTVariableDeclarator variableDeclarator : fieldDeclaration.findChildrenOfType(ASTVariableDeclarator.class)) {
    if (variableDeclarator.jjtGetNumChildren() > 1) {
      final Node variableInitializer=variableDeclarator.jjtGetChild(1);
      if (variableInitializer.jjtGetChild(0) instanceof ASTExpression) {
        final Node expression=variableInitializer.jjtGetChild(0);
        final Node primaryExpression;
        if (expression.jjtGetNumChildren() == 1) {
          if (expression.jjtGetChild(0) instanceof ASTPrimaryExpression) {
            primaryExpression=expression.jjtGetChild(0);
          }
 else           if (expression.jjtGetChild(0) instanceof ASTCastExpression && expression.jjtGetChild(0).jjtGetChild(1) instanceof ASTPrimaryExpression) {
            primaryExpression=expression.jjtGetChild(0).jjtGetChild(1);
          }
 else {
            continue;
          }
        }
 else {
          continue;
        }
        final Node primaryPrefix=primaryExpression.jjtGetChild(0);
        if (primaryPrefix.jjtGetNumChildren() == 1 && primaryPrefix.jjtGetChild(0) instanceof ASTLiteral) {
          final ASTLiteral literal=(ASTLiteral)primaryPrefix.jjtGetChild(0);
          if (isRef(fieldDeclaration,variableDeclarator)) {
            if (literal.jjtGetNumChildren() == 1 && literal.jjtGetChild(0) instanceof ASTNullLiteral) {
              addViolation(data,variableDeclarator);
            }
          }
 else {
            if (literal.jjtGetNumChildren() == 1 && literal.jjtGetChild(0) instanceof ASTBooleanLiteral) {
              ASTBooleanLiteral booleanLiteral=(ASTBooleanLiteral)literal.jjtGetChild(0);
              if (!booleanLiteral.isTrue()) {
                addViolation(data,variableDeclarator);
              }
            }
 else             if (literal.jjtGetNumChildren() == 0) {
              Number value=-1;
              if (literal.isIntLiteral()) {
                value=literal.getValueAsInt();
              }
 else               if (literal.isLongLiteral()) {
                value=literal.getValueAsLong();
              }
 else               if (literal.isFloatLiteral()) {
                value=literal.getValueAsFloat();
              }
 else               if (literal.isDoubleLiteral()) {
                value=literal.getValueAsDouble();
              }
 else               if (literal.isCharLiteral()) {
                value=(int)literal.getImage().charAt(1);
              }
              if (value.doubleValue() == 0) {
                addViolation(data,variableDeclarator);
              }
            }
          }
        }
      }
    }
  }
  return data;
}
