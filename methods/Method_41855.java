List<TriggerWrapper> getNextTriggerWrappers(final TimeTriggerSet source,final long noLaterThan,final int maxCount,final long timeWindow) throws JobPersistenceException {
  List<TriggerWrapper> wrappers=new ArrayList<TriggerWrapper>();
  Set<JobKey> acquiredJobKeysForNoConcurrentExec=new HashSet<JobKey>();
  Set<TriggerWrapper> excludedTriggers=new HashSet<TriggerWrapper>();
  JobPersistenceException caughtJpe=null;
  long batchEnd=noLaterThan;
  try {
    while (true) {
      TriggerWrapper tw=null;
      try {
        TriggerKey triggerKey=source.removeFirst();
        if (triggerKey != null) {
          tw=triggerFacade.get(triggerKey);
        }
        if (tw == null)         break;
      }
 catch (      java.util.NoSuchElementException nsee) {
        break;
      }
      if (tw.getNextFireTime() == null) {
        continue;
      }
      if (applyMisfire(tw)) {
        if (tw.getNextFireTime() != null) {
          source.add(tw);
        }
        continue;
      }
      if (tw.getNextFireTime().getTime() > batchEnd) {
        source.add(tw);
        break;
      }
      if (tw.jobDisallowsConcurrence()) {
        if (acquiredJobKeysForNoConcurrentExec.contains(tw.getJobKey())) {
          excludedTriggers.add(tw);
          continue;
        }
        acquiredJobKeysForNoConcurrentExec.add(tw.getJobKey());
      }
      if (wrappers.isEmpty()) {
        batchEnd=Math.max(tw.getNextFireTime().getTime(),System.currentTimeMillis()) + timeWindow;
      }
      wrappers.add(tw);
      if (wrappers.size() == maxCount) {
        break;
      }
    }
  }
 catch (  JobPersistenceException jpe) {
    caughtJpe=jpe;
  }
  if (excludedTriggers.size() > 0) {
    for (    TriggerWrapper tw : excludedTriggers) {
      source.add(tw);
    }
  }
  if (caughtJpe != null) {
    for (    TriggerWrapper tw : wrappers) {
      source.add(tw);
    }
    throw new JobPersistenceException("Exception encountered while trying to select triggers for firing.",caughtJpe);
  }
  return wrappers;
}
