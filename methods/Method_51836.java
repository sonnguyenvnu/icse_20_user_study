private int checkDecl(){
  if (jjtGetNumChildren() < 2 || !(jjtGetChild(1) instanceof ASTVariableDeclarator)) {
    return 0;
  }
  return ((ASTVariableDeclaratorId)jjtGetChild(1).jjtGetChild(0)).getArrayDepth();
}
