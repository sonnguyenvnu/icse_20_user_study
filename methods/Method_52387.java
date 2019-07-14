private static boolean isStringBuilderOrBuffer(ASTVariableDeclaratorId node){
  if (node.getType() != null) {
    return TypeHelper.isEither(node,StringBuffer.class,StringBuilder.class);
  }
  Node nn=node.getTypeNameNode();
  if (nn == null || nn.jjtGetNumChildren() == 0) {
    return false;
  }
  return TypeHelper.isEither((TypeNode)nn.jjtGetChild(0),StringBuffer.class,StringBuilder.class);
}
