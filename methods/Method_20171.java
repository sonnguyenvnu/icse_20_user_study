/** 
 * Returns a sub list of all items in  {@link #models} that occur after the given model. This listis backed by the original models list, any changes to the returned list will be reflected in the original  {@link #models} list.
 * @param model Must exist in {@link #models}.
 */
protected List<EpoxyModel<?>> getAllModelsAfter(EpoxyModel<?> model){
  int index=getModelPosition(model);
  if (index == -1) {
    throw new IllegalStateException("Model is not added: " + model);
  }
  return models.subList(index + 1,models.size());
}
