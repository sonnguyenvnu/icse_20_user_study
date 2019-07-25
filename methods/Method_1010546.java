private SNode convert(SModelReference source){
  SNode result=SModelOperations.createNewNode(myModel,null,MetaAdapterFactory.getConcept(0x86ef829012bb4ca7L,0x947f093788f263a9L,0x5869770da61dfe27L,"jetbrains.mps.lang.project.structure.ModelReference"));
  BHReflection.invoke0(result,MetaAdapterFactory.getConcept(0x86ef829012bb4ca7L,0x947f093788f263a9L,0x5869770da61dfe27L,"jetbrains.mps.lang.project.structure.ModelReference"),SMethodTrimmedId.create("populateFrom",MetaAdapterFactory.getConcept(0x86ef829012bb4ca7L,0x947f093788f263a9L,0x5869770da61dfe27L,"jetbrains.mps.lang.project.structure.ModelReference"),"2BHFktfniCd"),source);
  return result;
}
