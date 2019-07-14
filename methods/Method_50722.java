private boolean shouldHaveApexDocs(AbstractApexNode<?> node){
  if (!node.hasRealLoc()) {
    return false;
  }
  for (  final ASTAnnotation annotation : node.findDescendantsOfType(ASTAnnotation.class)) {
    if (annotation.getImage().equals("IsTest")) {
      return false;
    }
  }
  ASTModifierNode modifier=node.getFirstChildOfType(ASTModifierNode.class);
  if (modifier != null) {
    return (modifier.isPublic() || modifier.isGlobal()) && !modifier.isOverride();
  }
  return false;
}
