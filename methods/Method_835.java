/** 
 * Roll up the type based on type of the first and second child nodes using Binary Numeric Promotion per JLS 5.6.2
 * @param typeNode type node
 */
private void rollupTypeBinaryNumericPromotion(TypeNode typeNode){
  Node node=typeNode;
  if (node.jjtGetNumChildren() >= NumberConstants.INTEGER_SIZE_OR_LENGTH_2) {
    Node child1=node.jjtGetChild(0);
    Node child2=node.jjtGetChild(1);
    if (child1 instanceof TypeNode && child2 instanceof TypeNode) {
      Class<?> type1=((TypeNode)child1).getType();
      Class<?> type2=((TypeNode)child2).getType();
      if (type1 != null && type2 != null) {
        if (String.class.getName().equals(type1.getName()) || String.class.getName().equals(type2.getName())) {
          populateType(typeNode,String.class.getName());
        }
 else         if (boolean.class.getName().equals(type1.getName()) || boolean.class.getName().equals(type2.getName())) {
          populateType(typeNode,boolean.class.getName());
        }
 else         if (double.class.getName().equals(type1.getName()) || double.class.getName().equals(type2.getName())) {
          populateType(typeNode,double.class.getName());
        }
 else         if (float.class.getName().equals(type1.getName()) || float.class.getName().equals(type2.getName())) {
          populateType(typeNode,float.class.getName());
        }
 else         if (long.class.getName().equals(type1.getName()) || long.class.getName().equals(type2.getName())) {
          populateType(typeNode,long.class.getName());
        }
 else {
          populateType(typeNode,int.class.getName());
        }
      }
 else       if (type1 != null || type2 != null) {
        boolean populateString=type1 != null && String.class.getName().equals(type1.getName()) || type2 != null && String.class.getName().equals(type2.getName());
        if (populateString) {
          populateType(typeNode,String.class.getName());
        }
      }
    }
  }
}
