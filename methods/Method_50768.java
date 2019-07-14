private void reportViolation(ApexNode<?> node,Object data){
  ASTModifierNode modifier=node.getFirstChildOfType(ASTModifierNode.class);
  if (modifier != null) {
    if (localCacheOfReportedNodes.put(modifier,data) == null) {
      addViolation(data,modifier);
    }
  }
 else {
    if (localCacheOfReportedNodes.put(node,data) == null) {
      addViolation(data,node);
    }
  }
}
