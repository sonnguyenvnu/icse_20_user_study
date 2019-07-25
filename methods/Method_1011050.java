public void execute(SNode node){
  if (SNodeOperations.isInstanceOf(node,MetaAdapterFactory.getConcept(0xf3061a5392264cc5L,0xa443f952ceaf5816L,0xf8c77f1e96L,"jetbrains.mps.baseLanguage.structure.AssignmentExpression"))) {
    SNode assignmentExpression=SNodeOperations.cast(node,MetaAdapterFactory.getConcept(0xf3061a5392264cc5L,0xa443f952ceaf5816L,0xf8c77f1e96L,"jetbrains.mps.baseLanguage.structure.AssignmentExpression"));
    SNode lValue=SLinkOperations.getTarget(assignmentExpression,MetaAdapterFactory.getContainmentLink(0xf3061a5392264cc5L,0xa443f952ceaf5816L,0x11b0d00332cL,0xf8c77f1e97L,"lValue"));
    SNodeOperations.replaceWithAnother(assignmentExpression,lValue);
    if (SNodeOperations.isInstanceOf(SNodeOperations.getParent(lValue),MetaAdapterFactory.getConcept(0xf3061a5392264cc5L,0xa443f952ceaf5816L,0xf8cc56b213L,"jetbrains.mps.baseLanguage.structure.ExpressionStatement"))) {
      SNodeOperations.deleteNode(SNodeOperations.getParent(lValue));
    }
  }
  if (SNodeOperations.isInstanceOf(node,MetaAdapterFactory.getConcept(0xf3061a5392264cc5L,0xa443f952ceaf5816L,0xf8cc67c7efL,"jetbrains.mps.baseLanguage.structure.LocalVariableDeclaration"))) {
    SNodeOperations.deleteNode(SLinkOperations.getTarget(SNodeOperations.cast(node,MetaAdapterFactory.getConcept(0xf3061a5392264cc5L,0xa443f952ceaf5816L,0xf8cc67c7efL,"jetbrains.mps.baseLanguage.structure.LocalVariableDeclaration")),MetaAdapterFactory.getContainmentLink(0xf3061a5392264cc5L,0xa443f952ceaf5816L,0xf8c37a7f6eL,0xf8c37f506eL,"initializer")));
  }
  if (SNodeOperations.hasRole(node,MetaAdapterFactory.getContainmentLink(0xf3061a5392264cc5L,0xa443f952ceaf5816L,0xf8c37a7f6eL,0xf8c37f506eL,"initializer"))) {
    SNodeOperations.deleteNode(node);
  }
}
