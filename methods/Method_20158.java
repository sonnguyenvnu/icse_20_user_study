/** 
 * Adds the models to the end of the  {@link #models} list and notifies that the items wereinserted.
 */
protected void addModels(EpoxyModel<?>... modelsToAdd){
  int initialSize=models.size();
  int numModelsToAdd=modelsToAdd.length;
  ((ModelList)models).ensureCapacity(initialSize + numModelsToAdd);
  pauseModelListNotifications();
  Collections.addAll(models,modelsToAdd);
  resumeModelListNotifications();
  notifyItemRangeInserted(initialSize,numModelsToAdd);
}
