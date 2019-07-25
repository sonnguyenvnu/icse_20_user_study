@Override protected void save(){
  myModelProperties.saveChanges();
  myProject.getComponent(ModelGenerationStatusManager.class).invalidateData(Collections.singleton(myModelDescriptor));
  new MissingDependenciesFixer(myModelDescriptor).fixModuleDependencies();
  if (!(myModelDescriptor.getSource() instanceof NullDataSource)) {
    ((EditableSModel)myModelDescriptor).save();
  }
}
