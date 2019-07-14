private Object checkForNonExistentAnnotation(final AbstractApexNode<?> node,final ASTModifierNode modifierNode,final Object data){
  for (  ASTAnnotation annotation : modifierNode.findChildrenOfType(ASTAnnotation.class)) {
    if (!annotation.isResolved()) {
      addViolationWithMessage(data,node,"Use of non existent annotations will lead to broken Apex code which will not compile in the future.");
    }
  }
  return data;
}
