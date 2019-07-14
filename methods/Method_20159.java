/** 
 * Adds the models to the end of the  {@link #models} list and notifies that the items wereinserted.
 */
protected void addModels(Collection<? extends EpoxyModel<?>> modelsToAdd){
  int initialSize=models.size();
  pauseModelListNotifications();
  models.addAll(modelsToAdd);
  resumeModelListNotifications();
  notifyItemRangeInserted(initialSize,modelsToAdd.size());
}
