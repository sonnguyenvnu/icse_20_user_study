@Override public List<String> compute(){
  FileChooserDescriptor descriptor=new FileChooserDescriptor(true,true,true,true,false,true);
  final VirtualFile[] files=FileChooser.chooseFiles(descriptor,myComponent,null,null);
  List<String> result=new ArrayList<String>();
  for (  VirtualFile file : files) {
    ListSequence.fromList(result).addElement(FileUtil.toSystemIndependentName(file.getPath()));
  }
  if (ListSequence.fromList(result).isEmpty()) {
    return result;
  }
  if (myJavaOnly) {
    int res=Messages.showYesNoDialog(myComponent,"MPS can try creating models for the specified locations, so that class files can be referenced from MPS models directly. Would you like to import models for the specified locations?","Model Roots",Messages.getQuestionIcon());
    if (res == Messages.YES) {
      ListSequence.fromList(myRoots).addSequence(ListSequence.fromList(result).select(new ISelector<String,ModelRootDescriptor>(){
        public ModelRootDescriptor select(        String it){
          return ModelRootDescriptor.addSourceRoot(FileSystem.getInstance().getFile(it));
        }
      }
));
    }
  }
 else {
    final List<String> modelRootTypes=ListSequence.fromListWithValues(new ArrayList<String>(),PersistenceFacade.getInstance().getTypeIds());
    if (ListSequence.fromList(modelRootTypes).isEmpty()) {
      return result;
    }
    List<String> modelRootNames=ListSequence.fromList(modelRootTypes).select(new ISelector<String,String>(){
      public String select(      String it){
        return it;
      }
    }
).toListSequence();
    final int res=Messages.showChooseDialog(myComponent,"MPS can try creating models for the specified locations,\n" + "so that class files can be referenced from MPS models directly.\n" + "Would you like to import models for the specified locations?","Model Roots",ListSequence.fromList(modelRootNames).toGenericArray(String.class),ListSequence.fromList(modelRootNames).first(),Messages.getQuestionIcon());
    if (res >= 0) {
      ListSequence.fromList(myRoots).addSequence(ListSequence.fromList(result).select(new ISelector<String,ModelRootDescriptor>(){
        public ModelRootDescriptor select(        String it){
          String type=ListSequence.fromList(modelRootTypes).getElement(res);
          ModelRoot root=PersistenceFacade.getInstance().getModelRootFactory(type).create();
          Memento m=new MementoImpl();
          root.save(m);
          return new ModelRootDescriptor(type,m);
        }
      }
));
    }
  }
  return result;
}
