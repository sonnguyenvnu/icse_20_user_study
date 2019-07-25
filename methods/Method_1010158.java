public void forget(SModel model){
  myModelsToPublish.remove(model.getReference());
  myExactModelsToDrop.put(model,Boolean.TRUE);
}
