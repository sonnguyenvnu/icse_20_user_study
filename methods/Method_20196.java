@Override public void onResult(@NonNull DiffResult result){
  itemCount=result.newModels.size();
  notifyBlocker.allowChanges();
  result.dispatchTo(this);
  notifyBlocker.blockChanges();
  for (int i=modelBuildListeners.size() - 1; i >= 0; i--) {
    modelBuildListeners.get(i).onModelBuildFinished(result);
  }
}
