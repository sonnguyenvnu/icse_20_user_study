/** 
 * Inserts the given model after the other in the  {@link #models} list, and notifies that the itemwas inserted.
 */
protected void insertModelAfter(EpoxyModel<?> modelToInsert,EpoxyModel<?> modelToInsertAfter){
  int modelIndex=getModelPosition(modelToInsertAfter);
  if (modelIndex == -1) {
    throw new IllegalStateException("Model is not added: " + modelToInsertAfter);
  }
  int targetIndex=modelIndex + 1;
  pauseModelListNotifications();
  models.add(targetIndex,modelToInsert);
  resumeModelListNotifications();
  notifyItemInserted(targetIndex);
}
