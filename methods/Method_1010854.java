@Override public void navigate(boolean requestFocus){
  MPSPsiModel psiModel=extractPsiFromValue();
  SModelReference modelReference=psiModel.getSModelReference();
  SModel sModel=modelReference.resolve(ProjectHelper.getProjectRepository(getProject()));
  MPSPropertiesConfigurable configurable=new ModelPropertiesConfigurable(sModel,ProjectHelper.fromIdeaProject(MPSPsiModelTreeNode.this.getProject()),true);
  final SingleConfigurableEditor dialog=new SingleConfigurableEditor(myProject,configurable);
  configurable.setParentForCallBack(dialog);
  ApplicationManager.getApplication().invokeLater(dialog::show,ModalityState.current());
}
