/** 
 * Roll up the type based on type of the first child node using Unary Numeric Promotion per JLS 5.6.1
 * @param typeNode type node
 */
private void rollupTypeUnaryNumericPromotion(TypeNode typeNode){
  Node node=typeNode;
  if (node.jjtGetNumChildren() >= 1) {
    Node child=node.jjtGetChild(0);
    if (child instanceof TypeNode) {
      Class<?> type=((TypeNode)child).getType();
      if (type != null) {
        if (byte.class.getName().equals(type.getName()) || short.class.getName().equals(type.getName()) || char.class.getName().equals(type.getName())) {
          populateType(typeNode,int.class.getName());
        }
 else {
          typeNode.setType(((TypeNode)child).getType());
        }
      }
    }
  }
}
