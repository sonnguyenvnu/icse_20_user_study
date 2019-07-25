@Override public SNode wrap(SNode sourceNode){
  SNode nn=SModelOperations.createNewNode(SNodeOperations.getModel(sourceNode),null,MetaAdapterFactory.getConcept(0x798100da4f0a421aL,0xb99171f8c50ce5d2L,0x14d3fb6fb8480882L,"jetbrains.mps.build.structure.BuildSource_JavaFiles"));
  SLinkOperations.setTarget(nn,MetaAdapterFactory.getContainmentLink(0x798100da4f0a421aL,0xb99171f8c50ce5d2L,0x14d3fb6fb8480882L,0x14d3fb6fb8480883L,"resset"),sourceNode);
  return nn;
}
