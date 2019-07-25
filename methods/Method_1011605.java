@Override public SNode wrap(SNode sourceNode){
  SNode nn=SModelOperations.createNewNode(SNodeOperations.getModel(sourceNode),null,MetaAdapterFactory.getConcept(0x798100da4f0a421aL,0xb99171f8c50ce5d2L,0x3395e884b6185c40L,"jetbrains.mps.build.structure.BuildSource_JavaDependencyJar"));
  SLinkOperations.setTarget(nn,MetaAdapterFactory.getContainmentLink(0x798100da4f0a421aL,0xb99171f8c50ce5d2L,0x3395e884b6185c40L,0x3395e884b6185c41L,"jar"),sourceNode);
  return nn;
}
