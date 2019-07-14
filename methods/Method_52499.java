private void rollupTypeUnaryNumericPromotion(TypeNode typeNode){
  Node node=typeNode;
  if (node.jjtGetNumChildren() >= 1) {
    Node child=node.jjtGetChild(0);
    if (child instanceof TypeNode) {
      Class<?> type=((TypeNode)child).getType();
      if (type != null) {
        if ("byte".equals(type.getName()) || "short".equals(type.getName()) || "char".equals(type.getName())) {
          populateType(typeNode,"int");
        }
 else {
          typeNode.setType(((TypeNode)child).getType());
        }
      }
    }
  }
}
