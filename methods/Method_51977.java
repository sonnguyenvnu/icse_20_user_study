private String getMethodOrAttributeName(ASTPrimaryExpression node){
  ASTPrimaryPrefix prefix=node.getFirstDescendantOfType(ASTPrimaryPrefix.class);
  ASTName name=prefix.getFirstDescendantOfType(ASTName.class);
  String methodOrAttributeName=null;
  if (name != null) {
    int dotIndex=name.getImage().indexOf(".");
    if (dotIndex > -1) {
      methodOrAttributeName=name.getImage().substring(dotIndex + 1);
    }
  }
  return methodOrAttributeName;
}
