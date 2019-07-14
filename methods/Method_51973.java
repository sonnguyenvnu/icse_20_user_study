private boolean isForeignGetterSetterCall(ASTPrimaryExpression node){
  String methodOrAttributeName=getMethodOrAttributeName(node);
  return methodOrAttributeName != null && StringUtils.startsWithAny(methodOrAttributeName,"get","is","set");
}
