@Override public void stopDelayChecking(String groupId,String unitId){
  ScheduledFuture scheduledFuture=delayTasks.get(groupId + unitId);
  if (Objects.nonNull(scheduledFuture)) {
    txLogger.taskTrace(groupId,unitId,"cancel {}:{} checking.",groupId,unitId);
    scheduledFuture.cancel(true);
  }
}
