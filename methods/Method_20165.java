/** 
 * Shows the given models, and notifies that each item changed if the item wasn't already shown.
 * @param models The models to show. They should already be added to the {@link #models} list.
 */
protected void showModels(EpoxyModel<?>... models){
  showModels(Arrays.asList(models));
}
