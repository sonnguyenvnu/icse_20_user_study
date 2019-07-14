public int getArrayDepth(){
  ASTVariableDeclaratorId astVariableDeclaratorId=(ASTVariableDeclaratorId)node;
  ASTType typeNode=astVariableDeclaratorId.getTypeNode();
  if (typeNode != null) {
    return ((Dimensionable)typeNode.jjtGetParent()).getArrayDepth();
  }
 else {
    return 0;
  }
}
