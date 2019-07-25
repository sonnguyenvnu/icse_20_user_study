private void commit(JobMTask onlineTask){
  for (  JobMTask otask : onlineTask.getPostTask()) {
    otask.setCountDownLatch(onlineTask.getCountDownLatch());
    otask.getPreTaskCounter().getAndIncrement();
    if (otask.getPreTaskCounter().get() < otask.getPreTask().size()) {
      LOGGER.info(Constants.LOG_PREFIX + " The pre-tasks are not all completed, and the task is not started. {}",onlineTask.getTaskKey());
    }
 else {
      onlineTask.getPreTaskCounter().set(0);
      TaskCommit.commit(otask);
    }
  }
}
