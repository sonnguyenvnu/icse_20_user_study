/** 
 * <p> Get all of the Triggers that are associated to the given Job. </p> <p> If there are no matches, a zero-length array should be returned. </p>
 */
@Override public List<OperableTrigger> getTriggersForJob(final JobKey jobKey) throws JobPersistenceException {
  List<OperableTrigger> trigList=new ArrayList<OperableTrigger>();
  lock();
  try {
    for (    TriggerKey triggerKey : triggerFacade.allTriggerKeys()) {
      TriggerWrapper tw=triggerFacade.get(triggerKey);
      if (tw.getJobKey().equals(jobKey)) {
        trigList.add(tw.getTriggerClone());
      }
    }
  }
  finally {
    unlock();
  }
  return trigList;
}
