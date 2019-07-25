/** 
 * IMPORTANT API METHOD Tricky logic which is forced onto all of subclasses. This method represents a caching mechanism which does not reload the models which are already loaded but looks only at the difference between what we had and what we get now Strangely enough this logic is not in API (not added to the API #loadModels implementation) so the client of this class (and its subclasses) has to cast his <code>ModelRoot</code> to <code>ModelRootBase</code> every time he wants to reload the models from their data sources. TODO the right thing
 */
public void update(){
  assertCanChange();
  SModuleBase module=(SModuleBase)getModule();
  assert module != null;
  Set<SModelId> loaded=new HashSet<>();
  Iterable<SModel> allModels=loadModels();
  for (  SModel model : allModels) {
    SModel oldModel=module.getModel(model.getModelId());
    if (oldModel == model) {
    }
 else     if (oldModel != null && oldModel.getModelRoot() != model.getModelRoot()) {
      LOG.warn("Trying to load model `" + model + "' which is already loaded by another model root");
    }
 else     if (loaded.contains(model.getModelId())) {
      LOG.warn("loadModels() returned model `" + model + "' twice");
    }
 else {
      if (oldModel != null) {
        LOG.warn("loadModels() loaded model `" + model + "' which id is already present.");
        unregisterModel(oldModel);
      }
      registerModel(model);
    }
    loaded.add(model.getModelId());
  }
  Collection<SModel> models=new ArrayList<>(getModels());
  for (  SModel model : models) {
    if (!loaded.contains(model.getModelId())) {
      unregisterModel(model);
    }
  }
}
