private SNode convert(ModelRootDescriptor source){
  SNode result=SModelOperations.createNewNode(myModel,null,MetaAdapterFactory.getConcept(0x86ef829012bb4ca7L,0x947f093788f263a9L,0x19bfb4173fb52426L,"jetbrains.mps.lang.project.structure.ModelRoot"));
  SPropertyOperations.assign(result,MetaAdapterFactory.getProperty(0x86ef829012bb4ca7L,0x947f093788f263a9L,0x19bfb4173fb52426L,0x17221e2849561f98L,"type"),source.getType());
  String path=source.getMemento().get("path");
  if ((path != null && path.length() > 0)) {
    SPropertyOperations.assign(result,MetaAdapterFactory.getProperty(0x86ef829012bb4ca7L,0x947f093788f263a9L,0x19bfb4173fb52426L,0x19bfb4173fb5261fL,"path"),path);
  }
  return result;
}
