private SNode convert(SModuleReference ref){
  if (ref == null) {
    return null;
  }
  SNode result=SModelOperations.createNewNode(myModel,null,MetaAdapterFactory.getConcept(0x86ef829012bb4ca7L,0x947f093788f263a9L,0x19bfb4173fb5210cL,"jetbrains.mps.lang.project.structure.ModuleReference"));
  BHReflection.invoke0(result,MetaAdapterFactory.getConcept(0x86ef829012bb4ca7L,0x947f093788f263a9L,0x19bfb4173fb5210cL,"jetbrains.mps.lang.project.structure.ModuleReference"),SMethodTrimmedId.create("populateFrom",MetaAdapterFactory.getConcept(0x86ef829012bb4ca7L,0x947f093788f263a9L,0x19bfb4173fb5210cL,"jetbrains.mps.lang.project.structure.ModuleReference"),"2BHFktfnlSL"),ref);
  return result;
}
