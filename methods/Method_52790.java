private Node findTypeNameNode(Node node){
  ASTDatatype typeNode=(ASTDatatype)node.jjtGetChild(0);
  return typeNode.jjtGetChild(0);
}
