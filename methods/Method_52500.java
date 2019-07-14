private void rollupTypeBinaryNumericPromotion(TypeNode typeNode){
  Node node=typeNode;
  if (node.jjtGetNumChildren() >= 2) {
    Node child1=node.jjtGetChild(0);
    Node child2=node.jjtGetChild(1);
    if (child1 instanceof TypeNode && child2 instanceof TypeNode) {
      Class<?> type1=((TypeNode)child1).getType();
      Class<?> type2=((TypeNode)child2).getType();
      if (type1 != null && type2 != null) {
        if ("java.lang.String".equals(type1.getName()) || "java.lang.String".equals(type2.getName())) {
          populateType(typeNode,"java.lang.String");
        }
 else         if ("boolean".equals(type1.getName()) || "boolean".equals(type2.getName())) {
          populateType(typeNode,"boolean");
        }
 else         if ("double".equals(type1.getName()) || "double".equals(type2.getName())) {
          populateType(typeNode,"double");
        }
 else         if ("float".equals(type1.getName()) || "float".equals(type2.getName())) {
          populateType(typeNode,"float");
        }
 else         if ("long".equals(type1.getName()) || "long".equals(type2.getName())) {
          populateType(typeNode,"long");
        }
 else {
          populateType(typeNode,"int");
        }
      }
 else       if (type1 != null || type2 != null) {
        if (type1 != null && "java.lang.String".equals(type1.getName()) || type2 != null && "java.lang.String".equals(type2.getName())) {
          populateType(typeNode,"java.lang.String");
        }
      }
    }
  }
}
