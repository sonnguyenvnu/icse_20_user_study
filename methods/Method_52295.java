protected Object check(PropertyDescriptor<List<String>> property,Node node,Object data){
  Node parent=node.getNthParent(5);
  if (parent instanceof ASTForStatement) {
    if (hasPropertyValue(property,CHECK_FOR)) {
      super.addViolation(data,node);
    }
  }
 else   if (parent instanceof ASTWhileStatement) {
    if (hasPropertyValue(property,CHECK_WHILE)) {
      super.addViolation(data,node);
    }
  }
 else   if (parent instanceof ASTDoStatement) {
    if (hasPropertyValue(property,CHECK_DO)) {
      super.addViolation(data,node);
    }
  }
  return data;
}
