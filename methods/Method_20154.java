public void dispatchTo(ListUpdateCallback callback){
  if (differResult != null) {
    differResult.dispatchUpdatesTo(callback);
  }
 else   if (newModels.isEmpty() && !previousModels.isEmpty()) {
    callback.onRemoved(0,previousModels.size());
  }
 else   if (!newModels.isEmpty() && previousModels.isEmpty()) {
    callback.onInserted(0,newModels.size());
  }
}
