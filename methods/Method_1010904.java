@Override public SNode wrap(SNode sourceNode){
  SNode result=SNodeFactoryOperations.createNewNode(MetaAdapterFactory.getConcept(0xf3061a5392264cc5L,0xa443f952ceaf5816L,0x10ab8473cc5L,"jetbrains.mps.baseLanguage.structure.GenericNewExpression"),null);
  SLinkOperations.setTarget(result,MetaAdapterFactory.getContainmentLink(0xf3061a5392264cc5L,0xa443f952ceaf5816L,0x10ab8473cc5L,0x10ab847b486L,"creator"),sourceNode);
  return result;
}
