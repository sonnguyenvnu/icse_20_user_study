protected SNode cached(Object id){
  if (myDependencyHelper == null) {
    return null;
  }
  return SNodeOperations.as(myDependencyHelper.artifacts().get(id),MetaAdapterFactory.getConcept(0x798100da4f0a421aL,0xb99171f8c50ce5d2L,0x668c6cfbafac4c85L,"jetbrains.mps.build.structure.BuildLayout_Node"));
}
