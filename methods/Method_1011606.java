@Override public SNode wrap(SNode sourceNode){
  SNode nn=SModelOperations.createNewNode(SNodeOperations.getModel(sourceNode),null,MetaAdapterFactory.getConcept(0x798100da4f0a421aL,0xb99171f8c50ce5d2L,0x3395e884b61d4cbbL,"jetbrains.mps.build.structure.BuildSource_JavaLibraryCP"));
  SLinkOperations.setTarget(nn,MetaAdapterFactory.getContainmentLink(0x798100da4f0a421aL,0xb99171f8c50ce5d2L,0x3395e884b61d4cbbL,0x3395e884b61d4cbdL,"classpath"),sourceNode);
  return nn;
}
