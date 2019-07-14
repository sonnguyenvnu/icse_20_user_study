public boolean isArray(){
  ASTVariableDeclaratorId astVariableDeclaratorId=(ASTVariableDeclaratorId)node;
  ASTType typeNode=astVariableDeclaratorId.getTypeNode();
  if (typeNode != null) {
    return ((Dimensionable)typeNode.jjtGetParent()).isArray();
  }
 else {
    return false;
  }
}
