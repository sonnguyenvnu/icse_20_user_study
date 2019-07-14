protected List<OperableTrigger> acquireNextTrigger(Connection conn,long noLaterThan,int maxCount,long timeWindow) throws JobPersistenceException {
  if (timeWindow < 0) {
    throw new IllegalArgumentException();
  }
  List<OperableTrigger> acquiredTriggers=new ArrayList<OperableTrigger>();
  Set<JobKey> acquiredJobKeysForNoConcurrentExec=new HashSet<JobKey>();
  final int MAX_DO_LOOP_RETRY=3;
  int currentLoopCount=0;
  do {
    currentLoopCount++;
    try {
      List<TriggerKey> keys=getDelegate().selectTriggerToAcquire(conn,noLaterThan + timeWindow,getMisfireTime(),maxCount);
      if (keys == null || keys.size() == 0)       return acquiredTriggers;
      long batchEnd=noLaterThan;
      for (      TriggerKey triggerKey : keys) {
        OperableTrigger nextTrigger=retrieveTrigger(conn,triggerKey);
        if (nextTrigger == null) {
          continue;
        }
        JobKey jobKey=nextTrigger.getJobKey();
        JobDetail job;
        try {
          job=retrieveJob(conn,jobKey);
        }
 catch (        JobPersistenceException jpe) {
          try {
            getLog().error("Error retrieving job, setting trigger state to ERROR.",jpe);
            getDelegate().updateTriggerState(conn,triggerKey,STATE_ERROR);
          }
 catch (          SQLException sqle) {
            getLog().error("Unable to set trigger state to ERROR.",sqle);
          }
          continue;
        }
        if (job.isConcurrentExectionDisallowed()) {
          if (acquiredJobKeysForNoConcurrentExec.contains(jobKey)) {
            continue;
          }
 else {
            acquiredJobKeysForNoConcurrentExec.add(jobKey);
          }
        }
        Date nextFireTime=nextTrigger.getNextFireTime();
        if (nextFireTime == null) {
          log.warn("Trigger {} returned null on nextFireTime and yet still exists in DB!",nextTrigger.getKey());
          continue;
        }
        if (nextFireTime.getTime() > batchEnd) {
          break;
        }
        int rowsUpdated=getDelegate().updateTriggerStateFromOtherState(conn,triggerKey,STATE_ACQUIRED,STATE_WAITING);
        if (rowsUpdated <= 0) {
          continue;
        }
        nextTrigger.setFireInstanceId(getFiredTriggerRecordId());
        getDelegate().insertFiredTrigger(conn,nextTrigger,STATE_ACQUIRED,null);
        if (acquiredTriggers.isEmpty()) {
          batchEnd=Math.max(nextFireTime.getTime(),System.currentTimeMillis()) + timeWindow;
        }
        acquiredTriggers.add(nextTrigger);
      }
      if (acquiredTriggers.size() == 0 && currentLoopCount < MAX_DO_LOOP_RETRY) {
        continue;
      }
      break;
    }
 catch (    Exception e) {
      throw new JobPersistenceException("Couldn't acquire next trigger: " + e.getMessage(),e);
    }
  }
 while (true);
  return acquiredTriggers;
}
