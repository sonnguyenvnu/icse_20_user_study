@Override public List<SModelReference> compute(){
  List<SModelReference> models=new ModelAccessHelper(myProject.getModelAccess()).runReadAction(new Computable<List<SModelReference>>(){
    @Override public List<SModelReference> compute(){
      Iterable<SModel> descriptors=new FilteredGlobalScope().getModels();
      return Sequence.fromIterable(descriptors).select(new ISelector<SModel,SModelReference>(){
        public SModelReference select(        SModel it){
          return it.getReference();
        }
      }
).toListSequence();
    }
  }
);
  return CommonChoosers.showDialogModelCollectionChooser(ProjectHelper.toIdeaProject(myProject),models,null);
}
