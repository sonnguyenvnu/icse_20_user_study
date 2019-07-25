public void execute(SNode node){
  if (!(SNodeOperations.isInstanceOf(((SNode)AddExceptionToMethodSignature_QuickFix.this.getField("throwableType")[0]),MetaAdapterFactory.getConcept(0xf3061a5392264cc5L,0xa443f952ceaf5816L,0x101de48bf9eL,"jetbrains.mps.baseLanguage.structure.ClassifierType")))) {
    return;
  }
  SNode methodDecl=SNodeOperations.getNodeAncestor(node,MetaAdapterFactory.getConcept(0xf3061a5392264cc5L,0xa443f952ceaf5816L,0xf8cc56b1fcL,"jetbrains.mps.baseLanguage.structure.BaseMethodDeclaration"),false,false);
  ListSequence.fromList(SLinkOperations.getChildren(methodDecl,MetaAdapterFactory.getContainmentLink(0xf3061a5392264cc5L,0xa443f952ceaf5816L,0xf8cc56b1fcL,0x10f383d6949L,"throwsItem"))).addElement(SNodeOperations.cast(SNodeOperations.copyNode(((SNode)AddExceptionToMethodSignature_QuickFix.this.getField("throwableType")[0])),MetaAdapterFactory.getConcept(0xf3061a5392264cc5L,0xa443f952ceaf5816L,0xf8c37f506dL,"jetbrains.mps.baseLanguage.structure.Type")));
}
