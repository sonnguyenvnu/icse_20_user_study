@Override public SNode wrap(SNode sourceNode){
  SNode result=SNodeFactoryOperations.createNewNode(MetaAdapterFactory.getConcept(0xf3061a5392264cc5L,0xa443f952ceaf5816L,0xf8cc56b213L,"jetbrains.mps.baseLanguage.structure.ExpressionStatement"),null);
  SLinkOperations.setTarget(result,MetaAdapterFactory.getContainmentLink(0xf3061a5392264cc5L,0xa443f952ceaf5816L,0xf8cc56b213L,0xf8cc56b214L,"expression"),sourceNode);
  return result;
}
