public void dispose(SModel model){
  assert model instanceof TempModel : "TemporaryModels is asked to handle non-temporary model " + model.getModelName();
  TempModuleOptions module=myCreatedModels.remove(model);
  SModuleBase modelModule=(SModuleBase)model.getModule();
  if (modelModule != null) {
    modelModule.unregisterModel((SModelBase)model);
  }
  module.disposeModule();
}
