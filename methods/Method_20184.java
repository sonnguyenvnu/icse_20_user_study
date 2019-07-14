/** 
 * Staging models allows them to be implicitly added after the user finishes modifying them. This means that if a user has modified a model, and then moves on to modifying a different model, the first model is automatically added as soon as the second model is modified. <p> There are some edge cases for handling models that are added without modification, or models that are modified but then fail an `addIf` check. <p> This only works for AutoModels, and only if implicitly adding is enabled in configuration.
 */
void setStagedModel(EpoxyModel<?> model){
  if (model != stagedModel) {
    addCurrentlyStagedModelIfExists();
  }
  stagedModel=model;
}
