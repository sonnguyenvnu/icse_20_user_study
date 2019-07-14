/** 
 * Shows the given model, and notifies that the item changed if the item wasn't already shown.
 * @param model The model to show. It should already be added to the {@link #models} list.
 */
protected void showModel(EpoxyModel<?> model){
  showModel(model,true);
}
