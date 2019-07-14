private boolean isFirstChild(Node node,Class<?> clazz){
  return node.jjtGetNumChildren() == 1 && clazz.isAssignableFrom(node.jjtGetChild(0).getClass());
}
