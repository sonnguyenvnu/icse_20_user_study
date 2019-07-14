/** 
 * <p> Get a handle to the next trigger to be fired, and mark it as 'reserved' by the calling scheduler. </p>
 * @see #releaseAcquiredTrigger(OperableTrigger)
 */
public List<OperableTrigger> acquireNextTriggers(long noLaterThan,int maxCount,long timeWindow){
synchronized (lock) {
    List<OperableTrigger> result=new ArrayList<OperableTrigger>();
    Set<JobKey> acquiredJobKeysForNoConcurrentExec=new HashSet<JobKey>();
    Set<TriggerWrapper> excludedTriggers=new HashSet<TriggerWrapper>();
    long batchEnd=noLaterThan;
    if (timeTriggers.size() == 0)     return result;
    while (true) {
      TriggerWrapper tw;
      try {
        tw=timeTriggers.first();
        if (tw == null)         break;
        timeTriggers.remove(tw);
      }
 catch (      java.util.NoSuchElementException nsee) {
        break;
      }
      if (tw.trigger.getNextFireTime() == null) {
        continue;
      }
      if (applyMisfire(tw)) {
        if (tw.trigger.getNextFireTime() != null) {
          timeTriggers.add(tw);
        }
        continue;
      }
      if (tw.getTrigger().getNextFireTime().getTime() > batchEnd) {
        timeTriggers.add(tw);
        break;
      }
      JobKey jobKey=tw.trigger.getJobKey();
      JobDetail job=jobsByKey.get(tw.trigger.getJobKey()).jobDetail;
      if (job.isConcurrentExectionDisallowed()) {
        if (acquiredJobKeysForNoConcurrentExec.contains(jobKey)) {
          excludedTriggers.add(tw);
          continue;
        }
 else {
          acquiredJobKeysForNoConcurrentExec.add(jobKey);
        }
      }
      tw.state=TriggerWrapper.STATE_ACQUIRED;
      tw.trigger.setFireInstanceId(getFiredTriggerRecordId());
      OperableTrigger trig=(OperableTrigger)tw.trigger.clone();
      if (result.isEmpty()) {
        batchEnd=Math.max(tw.trigger.getNextFireTime().getTime(),System.currentTimeMillis()) + timeWindow;
      }
      result.add(trig);
      if (result.size() == maxCount)       break;
    }
    if (excludedTriggers.size() > 0)     timeTriggers.addAll(excludedTriggers);
    return result;
  }
}
