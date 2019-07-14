/** 
 * Sets the visibility of the given model, and notifies that the item changed if the new visibility is different from the previous.
 * @param model The model to show. It should already be added to the {@link #models} list.
 * @param show  True to show the model, false to hide it.
 */
protected void showModel(EpoxyModel<?> model,boolean show){
  if (model.isShown() == show) {
    return;
  }
  model.show(show);
  notifyModelChanged(model);
}
