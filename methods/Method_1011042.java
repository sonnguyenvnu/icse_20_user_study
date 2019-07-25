public void execute(SNode node){
  if (SLinkOperations.getTarget(SNodeOperations.cast(node,MetaAdapterFactory.getConcept(0xf3061a5392264cc5L,0xa443f952ceaf5816L,0x11a59b0fbceL,"jetbrains.mps.baseLanguage.structure.ClassCreator")),MetaAdapterFactory.getReferenceLink(0xf3061a5392264cc5L,0xa443f952ceaf5816L,0x11857355952L,0xf8c78301adL,"baseMethodDeclaration")) == null && ListSequence.fromList(SLinkOperations.getChildren(SNodeOperations.cast(node,MetaAdapterFactory.getConcept(0xf3061a5392264cc5L,0xa443f952ceaf5816L,0x11a59b0fbceL,"jetbrains.mps.baseLanguage.structure.ClassCreator")),MetaAdapterFactory.getContainmentLink(0xf3061a5392264cc5L,0xa443f952ceaf5816L,0x11857355952L,0xf8c78301aeL,"actualArgument"))).isEmpty()) {
    String refText=SLinkOperations.getResolveInfo(SNodeOperations.getReference(SNodeOperations.cast(node,MetaAdapterFactory.getConcept(0xf3061a5392264cc5L,0xa443f952ceaf5816L,0x11a59b0fbceL,"jetbrains.mps.baseLanguage.structure.ClassCreator")),MetaAdapterFactory.getReferenceLink(0xf3061a5392264cc5L,0xa443f952ceaf5816L,0x11857355952L,0xf8c78301adL,"baseMethodDeclaration")));
    if ((refText != null && refText.length() > 0)) {
      SNode clazz=SNodeOperations.cast(ClassifierScopes.getVisibleClassifiersWithDefaultConstructors(node).resolve(node,refText),MetaAdapterFactory.getConcept(0xf3061a5392264cc5L,0xa443f952ceaf5816L,0xf8c108ca66L,"jetbrains.mps.baseLanguage.structure.ClassConcept"));
      if ((clazz != null)) {
        SNode newCreator=_quotation_createNode_k3yd95_a0a0b0c0a0b(SLinkOperations.getChildren(SNodeOperations.cast(node,MetaAdapterFactory.getConcept(0xf3061a5392264cc5L,0xa443f952ceaf5816L,0x11a59b0fbceL,"jetbrains.mps.baseLanguage.structure.ClassCreator")),MetaAdapterFactory.getContainmentLink(0xf3061a5392264cc5L,0xa443f952ceaf5816L,0x11a59b0fbceL,0x11a59c8ffe0L,"typeParameter")),clazz);
        SNodeOperations.replaceWithAnother(node,newCreator);
      }
    }
  }
}
