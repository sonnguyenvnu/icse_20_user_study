private boolean hasTernaryNullCheck(ASTReturnStatement ret){
  ASTConditionalExpression condition=ret.getFirstDescendantOfType(ASTConditionalExpression.class);
  return condition.jjtGetChild(0) instanceof ASTEqualityExpression && condition.jjtGetChild(0).hasImageEqualTo("==") && condition.jjtGetChild(0).jjtGetChild(0).hasDescendantOfType(ASTName.class) && condition.jjtGetChild(0).jjtGetChild(1).hasDescendantOfType(ASTNullLiteral.class);
}
