public boolean contains(SModel model){
  if (model == null) {
    return false;
  }
  if (CollectionSequence.fromCollection(myModels).contains(model.getReference())) {
    return true;
  }
  return model.getModule() != null && CollectionSequence.fromCollection(myModules).contains(model.getModule().getModuleReference());
}
