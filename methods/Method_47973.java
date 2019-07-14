public void cancelTasks(){
  if (currentFetchTask != null)   currentFetchTask.cancel();
}
