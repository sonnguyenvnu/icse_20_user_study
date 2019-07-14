/** 
 * @see org.quartz.spi.JobStore#replaceTrigger(TriggerKey triggerKey,OperableTrigger newTrigger)
 */
public boolean replaceTrigger(TriggerKey triggerKey,OperableTrigger newTrigger) throws JobPersistenceException {
  boolean found;
synchronized (lock) {
    TriggerWrapper tw=triggersByKey.remove(triggerKey);
    found=(tw != null);
    if (found) {
      if (!tw.getTrigger().getJobKey().equals(newTrigger.getJobKey())) {
        throw new JobPersistenceException("New trigger is not related to the same job as the old trigger.");
      }
      HashMap<TriggerKey,TriggerWrapper> grpMap=triggersByGroup.get(triggerKey.getGroup());
      if (grpMap != null) {
        grpMap.remove(triggerKey);
        if (grpMap.size() == 0) {
          triggersByGroup.remove(triggerKey.getGroup());
        }
      }
      List<TriggerWrapper> jobList=triggersByJob.get(tw.jobKey);
      if (jobList != null) {
        jobList.remove(tw);
        if (jobList.isEmpty()) {
          triggersByJob.remove(tw.jobKey);
        }
      }
      timeTriggers.remove(tw);
      try {
        storeTrigger(newTrigger,false);
      }
 catch (      JobPersistenceException jpe) {
        storeTrigger(tw.getTrigger(),false);
        throw jpe;
      }
    }
  }
  return found;
}
