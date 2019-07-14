/** 
 * Inserts the given model before the other in the  {@link #models} list, and notifies that theitem was inserted.
 */
protected void insertModelBefore(EpoxyModel<?> modelToInsert,EpoxyModel<?> modelToInsertBefore){
  int targetIndex=getModelPosition(modelToInsertBefore);
  if (targetIndex == -1) {
    throw new IllegalStateException("Model is not added: " + modelToInsertBefore);
  }
  pauseModelListNotifications();
  models.add(targetIndex,modelToInsert);
  resumeModelListNotifications();
  notifyItemInserted(targetIndex);
}
