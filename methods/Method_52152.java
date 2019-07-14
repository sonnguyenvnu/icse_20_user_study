private static boolean isNotEquals(Node node){
  return node instanceof ASTEqualityExpression && "!=".equals(node.getImage());
}
