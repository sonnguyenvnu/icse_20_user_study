public void execute(SNode node){
  if (SNodeOperations.isInstanceOf(node,MetaAdapterFactory.getConcept(0x696c11654a59463bL,0xbc5d902caab85dd0L,0x1a6da65e8aea0b4L,"jetbrains.mps.make.facet.structure.ResourceClassifierType")) && !(SNodeOperations.isInstanceOf(SNodeOperations.getParent(node),MetaAdapterFactory.getConcept(0x696c11654a59463bL,0xbc5d902caab85dd0L,0x1a6da65e8aab1d4L,"jetbrains.mps.make.facet.structure.ResourceTypeDeclaration")))) {
    SNode resource=SLinkOperations.getTarget(SNodeOperations.cast(node,MetaAdapterFactory.getConcept(0x696c11654a59463bL,0xbc5d902caab85dd0L,0x1a6da65e8aea0b4L,"jetbrains.mps.make.facet.structure.ResourceClassifierType")),MetaAdapterFactory.getReferenceLink(0xf3061a5392264cc5L,0xa443f952ceaf5816L,0x101de48bf9eL,0x101de490babL,"classifier"));
    SNode replmnt=SNodeOperations.replaceWithNewChild(node,MetaAdapterFactory.getConcept(0xf3061a5392264cc5L,0xa443f952ceaf5816L,0x101de48bf9eL,"jetbrains.mps.baseLanguage.structure.ClassifierType"));
    SLinkOperations.setTarget(replmnt,MetaAdapterFactory.getReferenceLink(0xf3061a5392264cc5L,0xa443f952ceaf5816L,0x101de48bf9eL,0x101de490babL,"classifier"),resource);
  }
}
