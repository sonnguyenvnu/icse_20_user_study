public void refreshAllHabits(){
  if (currentFetchTask != null)   currentFetchTask.cancel();
  currentFetchTask=new RefreshTask();
  taskRunner.execute(currentFetchTask);
}
