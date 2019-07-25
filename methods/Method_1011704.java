public void execute(final MPSProject project,List<SNode> nodes){
  final SNode target=SNodeOperations.cast(ListSequence.fromList(nodes).first(),MetaAdapterFactory.getConcept(0xf3061a5392264cc5L,0xa443f952ceaf5816L,0x1107e0cb103L,"jetbrains.mps.baseLanguage.structure.AnonymousClass"));
  final Wrappers._T<String> classifierName=new Wrappers._T<String>();
  project.getRepository().getModelAccess().runReadAction(new Runnable(){
    public void run(){
      classifierName.value=SPropertyOperations.getString(SLinkOperations.getTarget(target,MetaAdapterFactory.getReferenceLink(0xf3061a5392264cc5L,0xa443f952ceaf5816L,0x1107e0cb103L,0x1107e0fd2a0L,"classifier")),MetaAdapterFactory.getProperty(0xceab519525ea4f22L,0x9b92103b95ca8c0cL,0x110396eaaa4L,0x110396ec041L,"name"));
    }
  }
);
  final String newName=StringChooserDialog.getString(project.getProject(),"Convert Anonymous Class","Class Name","My" + classifierName.value,true);
  if (newName == null) {
    return;
  }
  project.getRepository().getModelAccess().executeCommand(new Runnable(){
    public void run(){
      new ConvertAnonymousRefactoring(target,newName).doRefactor();
    }
  }
);
}
