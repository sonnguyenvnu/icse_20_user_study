public void execute(SNode node){
  if (!(SNodeOperations.isInstanceOf(node,MetaAdapterFactory.getConcept(0xf3061a5392264cc5L,0xa443f952ceaf5816L,0x115f7830a32a65e7L,"jetbrains.mps.baseLanguage.structure.ArrayClassExpression")))) {
    return;
  }
  SNode statedType=SLinkOperations.getTarget(SNodeOperations.cast(node,MetaAdapterFactory.getConcept(0xf3061a5392264cc5L,0xa443f952ceaf5816L,0x115f7830a32a65e7L,"jetbrains.mps.baseLanguage.structure.ArrayClassExpression")),MetaAdapterFactory.getContainmentLink(0xf3061a5392264cc5L,0xa443f952ceaf5816L,0x115f7830a32a65e7L,0x115f7830a32a65e8L,"arrayType"));
  if (SNodeOperations.isInstanceOf(statedType,MetaAdapterFactory.getConcept(0xf3061a5392264cc5L,0xa443f952ceaf5816L,0x101de48bf9eL,"jetbrains.mps.baseLanguage.structure.ClassifierType"))) {
    SNode replacing=SConceptOperations.createNewNode(MetaAdapterFactory.getConcept(0xf3061a5392264cc5L,0xa443f952ceaf5816L,0x103fb730c14L,"jetbrains.mps.baseLanguage.structure.ClassifierClassExpression"));
    SLinkOperations.setTarget(replacing,MetaAdapterFactory.getReferenceLink(0xf3061a5392264cc5L,0xa443f952ceaf5816L,0x103fb730c14L,0x103fb73a43eL,"classifier"),SLinkOperations.getTarget(SNodeOperations.cast(statedType,MetaAdapterFactory.getConcept(0xf3061a5392264cc5L,0xa443f952ceaf5816L,0x101de48bf9eL,"jetbrains.mps.baseLanguage.structure.ClassifierType")),MetaAdapterFactory.getReferenceLink(0xf3061a5392264cc5L,0xa443f952ceaf5816L,0x101de48bf9eL,0x101de490babL,"classifier")));
    SNodeOperations.replaceWithAnother(node,replacing);
  }
 else   if (SNodeOperations.isInstanceOf(statedType,MetaAdapterFactory.getConcept(0xf3061a5392264cc5L,0xa443f952ceaf5816L,0x10f0ad8bde4L,"jetbrains.mps.baseLanguage.structure.PrimitiveType"))) {
    SNode replacing=SConceptOperations.createNewNode(MetaAdapterFactory.getConcept(0xf3061a5392264cc5L,0xa443f952ceaf5816L,0x3f57ea36bd70a4e1L,"jetbrains.mps.baseLanguage.structure.PrimitiveClassExpression"));
    SLinkOperations.setTarget(replacing,MetaAdapterFactory.getContainmentLink(0xf3061a5392264cc5L,0xa443f952ceaf5816L,0x3f57ea36bd70a4e1L,0x3f57ea36bd70a4e2L,"primitiveType"),SNodeOperations.cast(statedType,MetaAdapterFactory.getConcept(0xf3061a5392264cc5L,0xa443f952ceaf5816L,0x10f0ad8bde4L,"jetbrains.mps.baseLanguage.structure.PrimitiveType")));
    SNodeOperations.replaceWithAnother(node,replacing);
  }
}
