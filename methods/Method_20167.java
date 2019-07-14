/** 
 * Sets the visibility of the given models, and notifies that the items changed if the new visibility is different from the previous.
 * @param models The models to show. They should already be added to the {@link #models} list.
 * @param show   True to show the models, false to hide them.
 */
protected void showModels(Iterable<EpoxyModel<?>> models,boolean show){
  for (  EpoxyModel<?> model : models) {
    showModel(model,show);
  }
}
