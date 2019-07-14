/** 
 * <p> Inform the <code>JobStore</code> that the scheduler has completed the firing of the given <code>Trigger</code> (and the execution its associated <code>Job</code>), and that the <code> {@link org.quartz.JobDataMap}</code> in the given <code>JobDetail</code> should be updated if the <code>Job</code> is stateful. </p>
 */
public void triggeredJobComplete(OperableTrigger trigger,JobDetail jobDetail,CompletedExecutionInstruction triggerInstCode){
synchronized (lock) {
    JobWrapper jw=jobsByKey.get(jobDetail.getKey());
    TriggerWrapper tw=triggersByKey.get(trigger.getKey());
    if (jw != null) {
      JobDetail jd=jw.jobDetail;
      if (jd.isPersistJobDataAfterExecution()) {
        JobDataMap newData=jobDetail.getJobDataMap();
        if (newData != null) {
          newData=(JobDataMap)newData.clone();
          newData.clearDirtyFlag();
        }
        jd=jd.getJobBuilder().setJobData(newData).build();
        jw.jobDetail=jd;
      }
      if (jd.isConcurrentExectionDisallowed()) {
        blockedJobs.remove(jd.getKey());
        ArrayList<TriggerWrapper> trigs=getTriggerWrappersForJob(jd.getKey());
        for (        TriggerWrapper ttw : trigs) {
          if (ttw.state == TriggerWrapper.STATE_BLOCKED) {
            ttw.state=TriggerWrapper.STATE_WAITING;
            timeTriggers.add(ttw);
          }
          if (ttw.state == TriggerWrapper.STATE_PAUSED_BLOCKED) {
            ttw.state=TriggerWrapper.STATE_PAUSED;
          }
        }
        signaler.signalSchedulingChange(0L);
      }
    }
 else {
      blockedJobs.remove(jobDetail.getKey());
    }
    if (tw != null) {
      if (triggerInstCode == CompletedExecutionInstruction.DELETE_TRIGGER) {
        if (trigger.getNextFireTime() == null) {
          if (tw.getTrigger().getNextFireTime() == null) {
            removeTrigger(trigger.getKey());
          }
        }
 else {
          removeTrigger(trigger.getKey());
          signaler.signalSchedulingChange(0L);
        }
      }
 else       if (triggerInstCode == CompletedExecutionInstruction.SET_TRIGGER_COMPLETE) {
        tw.state=TriggerWrapper.STATE_COMPLETE;
        timeTriggers.remove(tw);
        signaler.signalSchedulingChange(0L);
      }
 else       if (triggerInstCode == CompletedExecutionInstruction.SET_TRIGGER_ERROR) {
        getLog().info("Trigger " + trigger.getKey() + " set to ERROR state.");
        tw.state=TriggerWrapper.STATE_ERROR;
        signaler.signalSchedulingChange(0L);
      }
 else       if (triggerInstCode == CompletedExecutionInstruction.SET_ALL_JOB_TRIGGERS_ERROR) {
        getLog().info("All triggers of Job " + trigger.getJobKey() + " set to ERROR state.");
        setAllTriggersOfJobToState(trigger.getJobKey(),TriggerWrapper.STATE_ERROR);
        signaler.signalSchedulingChange(0L);
      }
 else       if (triggerInstCode == CompletedExecutionInstruction.SET_ALL_JOB_TRIGGERS_COMPLETE) {
        setAllTriggersOfJobToState(trigger.getJobKey(),TriggerWrapper.STATE_COMPLETE);
        signaler.signalSchedulingChange(0L);
      }
    }
  }
}
