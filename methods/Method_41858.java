/** 
 * <p> Inform the <code>JobStore</code> that the scheduler has completed the firing of the given <code>Trigger</code> (and the execution its associated <code>Job</code>), and that the <code> {@link org.quartz.JobDataMap}</code> in the given <code>JobDetail</code> should be updated if the <code>Job</code> is stateful. </p>
 */
@Override public void triggeredJobComplete(OperableTrigger trigger,JobDetail jobDetail,CompletedExecutionInstruction triggerInstCode){
  while (!toolkitShutdown) {
    try {
      lock();
      try {
        String fireId=trigger.getFireInstanceId();
        FiredTrigger removed=triggerFacade.removeFiredTrigger(fireId);
        if (removed == null) {
          getLog().warn("No fired trigger record found for " + trigger + " (" + fireId + ")");
          break;
        }
        JobKey jobKey=jobDetail.getKey();
        JobWrapper jw=jobFacade.get(jobKey);
        TriggerWrapper tw=triggerFacade.get(trigger.getKey());
        if (jw != null) {
          if (jw.isPersistJobDataAfterExecution()) {
            JobDataMap newData=jobDetail.getJobDataMap();
            if (newData != null) {
              newData=(JobDataMap)newData.clone();
              newData.clearDirtyFlag();
            }
            jw.setJobDataMap(newData,jobFacade);
          }
          if (jw.isConcurrentExectionDisallowed()) {
            jobFacade.removeBlockedJob(jw.getKey());
            tw.setState(TriggerState.WAITING,terracottaClientId,triggerFacade);
            timeTriggers.add(tw);
            List<TriggerWrapper> trigs=triggerFacade.getTriggerWrappersForJob(jw.getKey());
            for (            TriggerWrapper ttw : trigs) {
              if (ttw.getState() == TriggerState.BLOCKED) {
                ttw.setState(TriggerState.WAITING,terracottaClientId,triggerFacade);
                timeTriggers.add(ttw);
              }
              if (ttw.getState() == TriggerState.PAUSED_BLOCKED) {
                ttw.setState(TriggerState.PAUSED,terracottaClientId,triggerFacade);
              }
            }
            signaler.signalSchedulingChange(0L);
          }
        }
 else {
          jobFacade.removeBlockedJob(jobKey);
        }
        if (tw != null) {
          if (triggerInstCode == CompletedExecutionInstruction.DELETE_TRIGGER) {
            if (trigger.getNextFireTime() == null) {
              if (tw.getNextFireTime() == null) {
                removeTrigger(trigger.getKey());
              }
            }
 else {
              removeTrigger(trigger.getKey());
              signaler.signalSchedulingChange(0L);
            }
          }
 else           if (triggerInstCode == CompletedExecutionInstruction.SET_TRIGGER_COMPLETE) {
            tw.setState(TriggerState.COMPLETE,terracottaClientId,triggerFacade);
            timeTriggers.remove(tw);
            signaler.signalSchedulingChange(0L);
          }
 else           if (triggerInstCode == CompletedExecutionInstruction.SET_TRIGGER_ERROR) {
            getLog().info("Trigger " + trigger.getKey() + " set to ERROR state.");
            tw.setState(TriggerState.ERROR,terracottaClientId,triggerFacade);
            signaler.signalSchedulingChange(0L);
          }
 else           if (triggerInstCode == CompletedExecutionInstruction.SET_ALL_JOB_TRIGGERS_ERROR) {
            getLog().info("All triggers of Job " + trigger.getJobKey() + " set to ERROR state.");
            setAllTriggersOfJobToState(trigger.getJobKey(),TriggerState.ERROR);
            signaler.signalSchedulingChange(0L);
          }
 else           if (triggerInstCode == CompletedExecutionInstruction.SET_ALL_JOB_TRIGGERS_COMPLETE) {
            setAllTriggersOfJobToState(trigger.getJobKey(),TriggerState.COMPLETE);
            signaler.signalSchedulingChange(0L);
          }
        }
      }
  finally {
        unlock();
      }
    }
 catch (    RejoinException e) {
      try {
        Thread.sleep(retryInterval);
      }
 catch (      InterruptedException f) {
        throw new IllegalStateException("Received interrupted exception",f);
      }
      continue;
    }
catch (    JobPersistenceException e) {
      try {
        Thread.sleep(retryInterval);
      }
 catch (      InterruptedException f) {
        throw new IllegalStateException("Received interrupted exception",f);
      }
      continue;
    }
    break;
  }
}
