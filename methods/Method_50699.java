private Object checkForGlobal(ApexNode<?> node,Object data){
  ASTModifierNode modifierNode=node.getFirstChildOfType(ASTModifierNode.class);
  if (isGlobal(modifierNode) && !hasRestAnnotation(modifierNode) && !hasWebServices(node)) {
    addViolation(data,node);
  }
  return data;
}
