private void evalOrphanedTrigger(TriggerWrapper tw,boolean newNode){
  getLog().info("Evaluating orphaned trigger " + tw);
  JobWrapper jobWrapper=jobFacade.get(tw.getJobKey());
  if (jobWrapper == null) {
    getLog().error("No job found for orphaned trigger: " + tw);
    jobFacade.removeBlockedJob(tw.getJobKey());
    return;
  }
  if (newNode && tw.getState() == TriggerState.ERROR) {
    tw.setState(TriggerState.WAITING,terracottaClientId,triggerFacade);
    timeTriggers.add(tw);
  }
  if (tw.getState() == TriggerState.BLOCKED) {
    tw.setState(TriggerState.WAITING,terracottaClientId,triggerFacade);
    timeTriggers.add(tw);
  }
 else   if (tw.getState() == TriggerState.PAUSED_BLOCKED) {
    tw.setState(TriggerState.PAUSED,terracottaClientId,triggerFacade);
  }
  if (tw.getState() == TriggerState.ACQUIRED) {
    tw.setState(TriggerState.WAITING,terracottaClientId,triggerFacade);
    timeTriggers.add(tw);
  }
  if (!tw.mayFireAgain() && !jobWrapper.requestsRecovery()) {
    try {
      removeTrigger(tw.getKey());
    }
 catch (    JobPersistenceException e) {
      getLog().error("Can't remove completed trigger (and related job) " + tw,e);
    }
  }
  if (jobWrapper.isConcurrentExectionDisallowed()) {
    jobFacade.removeBlockedJob(jobWrapper.getKey());
    List<TriggerWrapper> triggersForJob=triggerFacade.getTriggerWrappersForJob(jobWrapper.getKey());
    for (    TriggerWrapper trigger : triggersForJob) {
      if (trigger.getState() == TriggerState.BLOCKED) {
        trigger.setState(TriggerState.WAITING,terracottaClientId,triggerFacade);
        timeTriggers.add(trigger);
      }
 else       if (trigger.getState() == TriggerState.PAUSED_BLOCKED) {
        trigger.setState(TriggerState.PAUSED,terracottaClientId,triggerFacade);
      }
    }
  }
}
