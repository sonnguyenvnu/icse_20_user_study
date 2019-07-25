public void process(){
  VisibleArtifacts artifacts=new VisibleArtifacts(project){
    @Override protected ArtifactLookup createLookup(){
      return new ArtifactLookup(this,new DependenciesHelper(FetchDependenciesProcessor.this.genContext,project));
    }
  }
;
  artifacts.collect(false);
  UnpackHelper helper=new UnpackHelper(artifacts,genContext);
  for (  SNode dep : SNodeOperations.getNodeDescendants(project,MetaAdapterFactory.getInterfaceConcept(0x798100da4f0a421aL,0xb99171f8c50ce5d2L,0xbabdfbeee1a36a3L,"jetbrains.mps.build.structure.BuildExternalDependency"),false,new SAbstractConcept[]{})) {
    BuildExternalDependency__BehaviorDescriptor.fetchDependencies_id57YmpYyL8F1.invoke(dep,artifacts,new FetchDependenciesProcessor.RequiredDependenciesBuilderImpl(artifacts,dep,helper));
  }
  helper.eval();
  List<SNode> statements=helper.getStatements();
  if (!(ListSequence.fromList(statements).isEmpty())) {
    SNode wf=SModelOperations.createNewNode(SNodeOperations.getModel(project),null,MetaAdapterFactory.getConcept(0x798100da4f0a421aL,0xb99171f8c50ce5d2L,0x4140393b23438dabL,"jetbrains.mps.build.structure.BuildCustomWorkflow"));
    SNode taskpart=SModelOperations.createNewNode(SNodeOperations.getModel(project),null,MetaAdapterFactory.getConcept(0x698a8d22a10447a0L,0xba8d10e3ec237f13L,0x36fb0dc9fd32c1b8L,"jetbrains.mps.build.workflow.structure.BwfTaskPart"));
    SLinkOperations.setPointer(taskpart,MetaAdapterFactory.getReferenceLink(0x698a8d22a10447a0L,0xba8d10e3ec237f13L,0x36fb0dc9fd32c1b8L,0x36fb0dc9fd32c1b9L,"task"),new SNodePointer("r:0d66e868-9778-4307-b6f9-4795c00f662f(jetbrains.mps.build.workflow.preset.general)","7128123785277844790"));
    ListSequence.fromList(SLinkOperations.getChildren(wf,MetaAdapterFactory.getContainmentLink(0x798100da4f0a421aL,0xb99171f8c50ce5d2L,0x4140393b23438dabL,0x4140393b23438dacL,"parts"))).addElement(taskpart);
    SNode stask=SModelOperations.createNewNode(SNodeOperations.getModel(project),null,MetaAdapterFactory.getConcept(0x698a8d22a10447a0L,0xba8d10e3ec237f13L,0x2670d5989d5a6275L,"jetbrains.mps.build.workflow.structure.BwfSubTask"));
    SPropertyOperations.assign(stask,MetaAdapterFactory.getProperty(0xceab519525ea4f22L,0x9b92103b95ca8c0cL,0x110396eaaa4L,0x110396ec041L,"name"),"fetch");
    ListSequence.fromList(SLinkOperations.getChildren(taskpart,MetaAdapterFactory.getContainmentLink(0x698a8d22a10447a0L,0xba8d10e3ec237f13L,0x36fb0dc9fd32c1b8L,0x36fb0dc9fd32c1baL,"subTasks"))).addElement(stask);
    ListSequence.fromList(SLinkOperations.getChildren(stask,MetaAdapterFactory.getContainmentLink(0x698a8d22a10447a0L,0xba8d10e3ec237f13L,0x2670d5989d5a6275L,0x2670d5989d5b4a62L,"statements"))).addSequence(ListSequence.fromList(statements));
    ListSequence.fromList(SLinkOperations.getChildren(project,MetaAdapterFactory.getContainmentLink(0x798100da4f0a421aL,0xb99171f8c50ce5d2L,0x4df58c6f18f84a13L,0x31292e1a60db57afL,"aspects"))).addElement(wf);
  }
}
