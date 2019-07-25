@Override public void navigate(final boolean requestFocus){
  final jetbrains.mps.project.Project mpsProject=ProjectHelper.fromIdeaProject(project);
  final SModelReference mr=new ModelAccessHelper(mpsProject.getModelAccess()).runReadAction(() -> {
    SModel model=lookupModel();
    return (model == null) ? null : model.getReference();
  }
);
  if (mr != null) {
    new EditorNavigator(mpsProject).shallFocus(requestFocus).shallSelect(true).open(new SNodePointer(mr,nodeId));
  }
}
