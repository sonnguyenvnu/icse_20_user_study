public void execute(SNode node){
  SNode snode=node;
  SNode blCast=SNodeOperations.cast(snode,MetaAdapterFactory.getConcept(0xf3061a5392264cc5L,0xa443f952ceaf5816L,0xf940dabe4aL,"jetbrains.mps.baseLanguage.structure.CastExpression"));
  SNode expr=SLinkOperations.getTarget(blCast,MetaAdapterFactory.getContainmentLink(0xf3061a5392264cc5L,0xa443f952ceaf5816L,0xf940dabe4aL,0xf940dabe4cL,"expression"));
  SNodeOperations.deleteNode(SLinkOperations.getTarget(blCast,MetaAdapterFactory.getContainmentLink(0xf3061a5392264cc5L,0xa443f952ceaf5816L,0xf940dabe4aL,0xf940dabe4cL,"expression")));
  SNode cast=SConceptOperations.createNewNode(MetaAdapterFactory.getConcept(0x7866978ea0f04cc7L,0x81bc4d213d9375e1L,0x10975850da7L,"jetbrains.mps.lang.smodel.structure.SNodeTypeCastExpression"));
  SLinkOperations.setTarget(cast,MetaAdapterFactory.getReferenceLink(0x7866978ea0f04cc7L,0x81bc4d213d9375e1L,0x2143399c0554e687L,0x5d71a86e0b67ce04L,"concept"),SLinkOperations.getTarget(SNodeOperations.cast(SLinkOperations.getTarget(blCast,MetaAdapterFactory.getContainmentLink(0xf3061a5392264cc5L,0xa443f952ceaf5816L,0xf940dabe4aL,0xf940dabe4bL,"type")),MetaAdapterFactory.getConcept(0x7866978ea0f04cc7L,0x81bc4d213d9375e1L,0x108f968b3caL,"jetbrains.mps.lang.smodel.structure.SNodeType")),MetaAdapterFactory.getReferenceLink(0x7866978ea0f04cc7L,0x81bc4d213d9375e1L,0x108f968b3caL,0x1090e46ca51L,"concept")));
  SNodeOperations.replaceWithAnother(snode,cast);
  SLinkOperations.setTarget(cast,MetaAdapterFactory.getContainmentLink(0x7866978ea0f04cc7L,0x81bc4d213d9375e1L,0x2143399c0554e687L,0x5d71a86e0b67cd19L,"leftExpression"),expr);
}
