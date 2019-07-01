public Boolean _XXXXX_(String repositoryId,boolean fullScan){
  if (getRepositoryTaskScheduler().isProcessingRepositoryTask(repositoryId)) {
    log.info("scanning of repository with id {} already scheduled",repositoryId);
  }
  RepositoryTask task=new RepositoryTask();
  task.setRepositoryId(repositoryId);
  task.setScanAll(fullScan);
  try {
    getRepositoryTaskScheduler().queueTask(task);
  }
 catch (  TaskQueueException e) {
    log.error("failed to schedule scanning of repo with id {}",repositoryId,e);
    return false;
  }
  return true;
}