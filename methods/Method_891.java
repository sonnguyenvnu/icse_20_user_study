@Override public Object visit(ASTMethodDeclaration node,Object data){
  try {
    List<Node> astPrimitiveTypeList=node.findChildNodesWithXPath(METHOD_RETURN_TYPE_XPATH);
    if (!(astPrimitiveTypeList != null && astPrimitiveTypeList.size() == 1)) {
      return super.visit(node,data);
    }
    ASTPrimitiveType astPrimitiveType=(ASTPrimitiveType)astPrimitiveTypeList.get(0);
    if (!(astPrimitiveType.getType() != null && astPrimitiveType.getType().isPrimitive())) {
      return super.visit(node,data);
    }
    String primitiveTypeName=astPrimitiveType.getType().getName();
    List<Node> nameList=node.findChildNodesWithXPath(METHOD_RETURN_OBJECT_XPATH);
    if (nameList == null || nameList.size() != 1) {
      return super.visit(node,data);
    }
    List<Node> methodVariableNameList=node.findChildNodesWithXPath(METHOD_VARIABLE_NAME_XPATH);
    if (methodVariableNameList == null || methodVariableNameList.size() == 0) {
      return super.visit(node,data);
    }
    ASTName astName=(ASTName)nameList.get(0);
    String variableName=astName.getImage();
    for (    Node methodVariableNameNode : methodVariableNameList) {
      ASTVariableDeclaratorId astVariableDeclaratorId=(ASTVariableDeclaratorId)methodVariableNameNode;
      if (!variableName.equals(astVariableDeclaratorId.getImage())) {
        continue;
      }
      ASTLocalVariableDeclaration astLocalVariableDeclaration=astVariableDeclaratorId.getFirstParentOfType(ASTLocalVariableDeclaration.class);
      List<Node> nodeList=astLocalVariableDeclaration.findChildNodesWithXPath(METHOD_VARIABLE_TYPE_XPATH);
      if (nodeList != null && nodeList.size() == 1) {
        ASTClassOrInterfaceType astClassOrInterfaceType=(ASTClassOrInterfaceType)nodeList.get(0);
        if (PRIMITIVE_TYPE_TO_WAPPER_TYPE.get(primitiveTypeName) != null && PRIMITIVE_TYPE_TO_WAPPER_TYPE.get(primitiveTypeName).equals(astClassOrInterfaceType.getType().getSimpleName())) {
          ViolationUtils.addViolationWithPrecisePosition(this,node,data,I18nResources.getMessage("java.exception.MethodReturnWrapperTypeRule.violation.msg",primitiveTypeName,astClassOrInterfaceType.getType().getSimpleName()));
        }
      }
    }
  }
 catch (  JaxenException e) {
    return super.visit(node,data);
  }
  return super.visit(node,data);
}
