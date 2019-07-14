private boolean hasWebServices(ApexNode<?> node){
  List<ASTMethod> methods=node.findChildrenOfType(ASTMethod.class);
  for (  ASTMethod method : methods) {
    ASTModifierNode methodModifier=method.getFirstChildOfType(ASTModifierNode.class);
    if (isWebService(methodModifier)) {
      return true;
    }
  }
  return false;
}
