/** 
 * Failure strategy - fragmentation processing logic
 * @param onlineTask
 * @param throwable
 * @return
 */
@LogAnnotation public boolean sharding(JobMTask onlineTask,Throwable throwable){
  LOGGER.info(Constants.LOG_PREFIX + " Execution task failed >>> Failure strategy?sharding ->->->  task is {}, jobKey is {}",onlineTask.getTaskKey(),onlineTask.getJobKey());
  ExecutorRouteSharding.release(onlineTask,false);
  if (ExecutorRouteSharding.maxExecuteCount(onlineTask) >= 0) {
    return true;
  }
  TaskCommit.commit(onlineTask);
  return false;
}
