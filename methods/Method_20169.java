/** 
 * Hides the given models, and notifies that each item changed if the item wasn't already hidden.
 * @param models The models to hide. They should already be added to the {@link #models} list.
 */
protected void hideModels(Iterable<EpoxyModel<?>> models){
  showModels(models,false);
}
