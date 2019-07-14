@SuppressWarnings("ConstantConditions") protected void clusterRecover(Connection conn,List<SchedulerStateRecord> failedInstances) throws JobPersistenceException {
  if (failedInstances.size() > 0) {
    long recoverIds=System.currentTimeMillis();
    logWarnIfNonZero(failedInstances.size(),"ClusterManager: detected " + failedInstances.size() + " failed or restarted instances.");
    try {
      for (      SchedulerStateRecord rec : failedInstances) {
        getLog().info("ClusterManager: Scanning for instance \"" + rec.getSchedulerInstanceId() + "\"'s failed in-progress jobs.");
        List<FiredTriggerRecord> firedTriggerRecs=getDelegate().selectInstancesFiredTriggerRecords(conn,rec.getSchedulerInstanceId());
        int acquiredCount=0;
        int recoveredCount=0;
        int otherCount=0;
        Set<TriggerKey> triggerKeys=new HashSet<TriggerKey>();
        for (        FiredTriggerRecord ftRec : firedTriggerRecs) {
          TriggerKey tKey=ftRec.getTriggerKey();
          JobKey jKey=ftRec.getJobKey();
          triggerKeys.add(tKey);
          if (ftRec.getFireInstanceState().equals(STATE_BLOCKED)) {
            getDelegate().updateTriggerStatesForJobFromOtherState(conn,jKey,STATE_WAITING,STATE_BLOCKED);
          }
 else           if (ftRec.getFireInstanceState().equals(STATE_PAUSED_BLOCKED)) {
            getDelegate().updateTriggerStatesForJobFromOtherState(conn,jKey,STATE_PAUSED,STATE_PAUSED_BLOCKED);
          }
          if (ftRec.getFireInstanceState().equals(STATE_ACQUIRED)) {
            getDelegate().updateTriggerStateFromOtherState(conn,tKey,STATE_WAITING,STATE_ACQUIRED);
            acquiredCount++;
          }
 else           if (ftRec.isJobRequestsRecovery()) {
            if (jobExists(conn,jKey)) {
              @SuppressWarnings("deprecation") SimpleTriggerImpl rcvryTrig=new SimpleTriggerImpl("recover_" + rec.getSchedulerInstanceId() + "_" + String.valueOf(recoverIds++),Scheduler.DEFAULT_RECOVERY_GROUP,new Date(ftRec.getScheduleTimestamp()));
              rcvryTrig.setJobName(jKey.getName());
              rcvryTrig.setJobGroup(jKey.getGroup());
              rcvryTrig.setMisfireInstruction(SimpleTrigger.MISFIRE_INSTRUCTION_IGNORE_MISFIRE_POLICY);
              rcvryTrig.setPriority(ftRec.getPriority());
              JobDataMap jd=getDelegate().selectTriggerJobDataMap(conn,tKey.getName(),tKey.getGroup());
              jd.put(Scheduler.FAILED_JOB_ORIGINAL_TRIGGER_NAME,tKey.getName());
              jd.put(Scheduler.FAILED_JOB_ORIGINAL_TRIGGER_GROUP,tKey.getGroup());
              jd.put(Scheduler.FAILED_JOB_ORIGINAL_TRIGGER_FIRETIME_IN_MILLISECONDS,String.valueOf(ftRec.getFireTimestamp()));
              jd.put(Scheduler.FAILED_JOB_ORIGINAL_TRIGGER_SCHEDULED_FIRETIME_IN_MILLISECONDS,String.valueOf(ftRec.getScheduleTimestamp()));
              rcvryTrig.setJobDataMap(jd);
              rcvryTrig.computeFirstFireTime(null);
              storeTrigger(conn,rcvryTrig,null,false,STATE_WAITING,false,true);
              recoveredCount++;
            }
 else {
              getLog().warn("ClusterManager: failed job '" + jKey + "' no longer exists, cannot schedule recovery.");
              otherCount++;
            }
          }
 else {
            otherCount++;
          }
          if (ftRec.isJobDisallowsConcurrentExecution()) {
            getDelegate().updateTriggerStatesForJobFromOtherState(conn,jKey,STATE_WAITING,STATE_BLOCKED);
            getDelegate().updateTriggerStatesForJobFromOtherState(conn,jKey,STATE_PAUSED,STATE_PAUSED_BLOCKED);
          }
        }
        getDelegate().deleteFiredTriggers(conn,rec.getSchedulerInstanceId());
        int completeCount=0;
        for (        TriggerKey triggerKey : triggerKeys) {
          if (getDelegate().selectTriggerState(conn,triggerKey).equals(STATE_COMPLETE)) {
            List<FiredTriggerRecord> firedTriggers=getDelegate().selectFiredTriggerRecords(conn,triggerKey.getName(),triggerKey.getGroup());
            if (firedTriggers.isEmpty()) {
              if (removeTrigger(conn,triggerKey)) {
                completeCount++;
              }
            }
          }
        }
        logWarnIfNonZero(acquiredCount,"ClusterManager: ......Freed " + acquiredCount + " acquired trigger(s).");
        logWarnIfNonZero(completeCount,"ClusterManager: ......Deleted " + completeCount + " complete triggers(s).");
        logWarnIfNonZero(recoveredCount,"ClusterManager: ......Scheduled " + recoveredCount + " recoverable job(s) for recovery.");
        logWarnIfNonZero(otherCount,"ClusterManager: ......Cleaned-up " + otherCount + " other failed job(s).");
        if (!rec.getSchedulerInstanceId().equals(getInstanceId())) {
          getDelegate().deleteSchedulerState(conn,rec.getSchedulerInstanceId());
        }
      }
    }
 catch (    Throwable e) {
      throw new JobPersistenceException("Failure recovering jobs: " + e.getMessage(),e);
    }
  }
}
