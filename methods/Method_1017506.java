public void start(String taskId){
  if (taskId != null) {
    isDataLoading=true;
    liveTask=tasksRepository.getTaskWithChanges(taskId);
    liveTask.observeForever(this);
  }
}
