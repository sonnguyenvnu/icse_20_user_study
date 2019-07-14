/** 
 * Hides the given model, and notifies that the item changed if the item wasn't already hidden.
 * @param model The model to hide. This should already be added to the {@link #models} list.
 */
protected void hideModel(EpoxyModel<?> model){
  showModel(model,false);
}
