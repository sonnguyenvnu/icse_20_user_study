/** 
 * Removes all models
 */
protected void removeAllModels(){
  int numModelsRemoved=models.size();
  pauseModelListNotifications();
  models.clear();
  resumeModelListNotifications();
  notifyItemRangeRemoved(0,numModelsRemoved);
}
