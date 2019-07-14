/** 
 * @see org.quartz.spi.JobStore#replaceTrigger
 */
@Override public boolean replaceTrigger(TriggerKey triggerKey,OperableTrigger newTrigger) throws JobPersistenceException {
  boolean found=false;
  lock();
  try {
    TriggerWrapper tw=triggerFacade.remove(triggerKey);
    found=tw != null;
    if (tw != null) {
      if (!tw.getJobKey().equals(newTrigger.getJobKey())) {
        throw new JobPersistenceException("New trigger is not related to the same job as the old trigger.");
      }
      Set<String> grpSet=toolkitDSHolder.getOrCreateTriggersGroupMap(triggerKey.getGroup());
      grpSet.remove(triggerKey.getName());
      if (grpSet.size() == 0) {
        toolkitDSHolder.removeTriggersGroupMap(triggerKey.getGroup());
        triggerFacade.removeGroup(triggerKey.getGroup());
      }
      timeTriggers.remove(tw);
      try {
        storeTrigger(newTrigger,false);
      }
 catch (      JobPersistenceException jpe) {
        storeTrigger(tw.getTriggerClone(),false);
        throw jpe;
      }
    }
  }
  finally {
    unlock();
  }
  return found;
}
