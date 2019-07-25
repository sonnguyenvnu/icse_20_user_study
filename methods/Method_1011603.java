@Override public SNode wrap(SNode sourceNode){
  SNode cp=SModelOperations.createNewNode(SNodeOperations.getModel(sourceNode),null,MetaAdapterFactory.getConcept(0x798100da4f0a421aL,0xb99171f8c50ce5d2L,0x48d5d03db92339b9L,"jetbrains.mps.build.structure.BuildLayout_Copy"));
  SLinkOperations.setTarget(cp,MetaAdapterFactory.getContainmentLink(0x798100da4f0a421aL,0xb99171f8c50ce5d2L,0x7f76698a394d9b91L,0x48d5d03db92339baL,"fileset"),sourceNode);
  return cp;
}
