private ASTVariableDeclaratorId getDecl(){
  return (ASTVariableDeclaratorId)jjtGetChild(jjtGetNumChildren() - 1).jjtGetChild(0);
}
