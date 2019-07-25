private Boolean run(JobMTask onlineTask){
  if (Constants.ENDTASK.equals(onlineTask.getTaskKey())) {
    return runEndTask(onlineTask);
  }
  return runTask(onlineTask);
}
