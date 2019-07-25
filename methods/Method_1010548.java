private SNode convert(SDependency source){
  SNode dep=SModelOperations.createNewNode(myModel,null,MetaAdapterFactory.getConcept(0x86ef829012bb4ca7L,0x947f093788f263a9L,0x5869770da61dfe28L,"jetbrains.mps.lang.project.structure.ModuleDependency"));
  SPropertyOperations.assign(dep,MetaAdapterFactory.getProperty(0x86ef829012bb4ca7L,0x947f093788f263a9L,0x5869770da61dfe28L,0x5869770da61dfe29L,"reexport"),source.isReexport());
  SLinkOperations.setTarget(dep,MetaAdapterFactory.getContainmentLink(0x86ef829012bb4ca7L,0x947f093788f263a9L,0x5869770da61dfe28L,0x19bfb4173fb5241eL,"moduleRef"),convert(source.getTargetModule()));
  return dep;
}
