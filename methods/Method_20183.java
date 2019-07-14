/** 
 * Method to actually add the model to the list being built. Should be called after all validations are done.
 */
void addInternal(EpoxyModel<?> modelToAdd){
  assertIsBuildingModels();
  if (modelToAdd.hasDefaultId()) {
    throw new IllegalEpoxyUsage("You must set an id on a model before adding it. Use the @AutoModel annotation if you " + "want an id to be automatically generated for you.");
  }
  if (!modelToAdd.isShown()) {
    throw new IllegalEpoxyUsage("You cannot hide a model in an EpoxyController. Use `addIf` to conditionally add a " + "model instead.");
  }
  clearModelFromStaging(modelToAdd);
  modelToAdd.controllerToStageTo=null;
  modelsBeingBuilt.add(modelToAdd);
}
