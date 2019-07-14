/** 
 * Hides all models currently located after the given model in the  {@link #models} list.
 * @param model The model after which to hide. It must exist in the {@link #models} list.
 */
protected void hideAllAfterModel(EpoxyModel<?> model){
  hideModels(getAllModelsAfter(model));
}
