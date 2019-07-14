@Override public void removeHiTask(String taskId){
  historyService.deleteHistoricTaskInstance(taskId);
}
