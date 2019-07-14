protected void triggeredJobComplete(Connection conn,OperableTrigger trigger,JobDetail jobDetail,CompletedExecutionInstruction triggerInstCode) throws JobPersistenceException {
  try {
    if (triggerInstCode == CompletedExecutionInstruction.DELETE_TRIGGER) {
      if (trigger.getNextFireTime() == null) {
        TriggerStatus stat=getDelegate().selectTriggerStatus(conn,trigger.getKey());
        if (stat != null && stat.getNextFireTime() == null) {
          removeTrigger(conn,trigger.getKey());
        }
      }
 else {
        removeTrigger(conn,trigger.getKey());
        signalSchedulingChangeOnTxCompletion(0L);
      }
    }
 else     if (triggerInstCode == CompletedExecutionInstruction.SET_TRIGGER_COMPLETE) {
      getDelegate().updateTriggerState(conn,trigger.getKey(),STATE_COMPLETE);
      signalSchedulingChangeOnTxCompletion(0L);
    }
 else     if (triggerInstCode == CompletedExecutionInstruction.SET_TRIGGER_ERROR) {
      getLog().info("Trigger " + trigger.getKey() + " set to ERROR state.");
      getDelegate().updateTriggerState(conn,trigger.getKey(),STATE_ERROR);
      signalSchedulingChangeOnTxCompletion(0L);
    }
 else     if (triggerInstCode == CompletedExecutionInstruction.SET_ALL_JOB_TRIGGERS_COMPLETE) {
      getDelegate().updateTriggerStatesForJob(conn,trigger.getJobKey(),STATE_COMPLETE);
      signalSchedulingChangeOnTxCompletion(0L);
    }
 else     if (triggerInstCode == CompletedExecutionInstruction.SET_ALL_JOB_TRIGGERS_ERROR) {
      getLog().info("All triggers of Job " + trigger.getKey() + " set to ERROR state.");
      getDelegate().updateTriggerStatesForJob(conn,trigger.getJobKey(),STATE_ERROR);
      signalSchedulingChangeOnTxCompletion(0L);
    }
    if (jobDetail.isConcurrentExectionDisallowed()) {
      getDelegate().updateTriggerStatesForJobFromOtherState(conn,jobDetail.getKey(),STATE_WAITING,STATE_BLOCKED);
      getDelegate().updateTriggerStatesForJobFromOtherState(conn,jobDetail.getKey(),STATE_PAUSED,STATE_PAUSED_BLOCKED);
      signalSchedulingChangeOnTxCompletion(0L);
    }
    if (jobDetail.isPersistJobDataAfterExecution()) {
      try {
        if (jobDetail.getJobDataMap().isDirty()) {
          getDelegate().updateJobData(conn,jobDetail);
        }
      }
 catch (      IOException e) {
        throw new JobPersistenceException("Couldn't serialize job data: " + e.getMessage(),e);
      }
catch (      SQLException e) {
        throw new JobPersistenceException("Couldn't update job data: " + e.getMessage(),e);
      }
    }
  }
 catch (  SQLException e) {
    throw new JobPersistenceException("Couldn't update trigger state(s): " + e.getMessage(),e);
  }
  try {
    getDelegate().deleteFiredTrigger(conn,trigger.getFireInstanceId());
  }
 catch (  SQLException e) {
    throw new JobPersistenceException("Couldn't delete fired trigger: " + e.getMessage(),e);
  }
}
