/** 
 * <p> Store the given <code> {@link org.quartz.Trigger}</code>. </p>
 * @param newTrigger The <code>Trigger</code> to be stored.
 * @param replaceExisting If <code>true</code>, any <code>Trigger</code> existing in the <code>JobStore</code> withthe same name & group should be over-written.
 * @throws ObjectAlreadyExistsException if a <code>Trigger</code> with the same name/group already exists, andreplaceExisting is set to false.
 * @see #pauseTriggers(org.quartz.impl.matchers.GroupMatcher)
 */
@Override public void storeTrigger(OperableTrigger newTrigger,boolean replaceExisting) throws JobPersistenceException {
  OperableTrigger clone=(OperableTrigger)newTrigger.clone();
  lock();
  try {
    JobDetail job=retrieveJob(newTrigger.getJobKey());
    if (job == null) {
      throw new JobPersistenceException("The job (" + newTrigger.getJobKey() + ") referenced by the trigger does not exist.");
    }
    TriggerWrapper tw=wrapperFactory.createTriggerWrapper(clone,job.isConcurrentExectionDisallowed());
    if (triggerFacade.containsKey(tw.getKey())) {
      if (!replaceExisting) {
        throw new ObjectAlreadyExistsException(newTrigger);
      }
      removeTrigger(newTrigger.getKey(),false);
    }
    Set<String> grpSet=toolkitDSHolder.getOrCreateTriggersGroupMap(newTrigger.getKey().getGroup());
    grpSet.add(newTrigger.getKey().getName());
    if (!triggerFacade.hasGroup(newTrigger.getKey().getGroup())) {
      triggerFacade.addGroup(newTrigger.getKey().getGroup());
    }
    if (triggerFacade.pausedGroupsContain(newTrigger.getKey().getGroup()) || jobFacade.pausedGroupsContain(newTrigger.getJobKey().getGroup())) {
      tw.setState(TriggerState.PAUSED,terracottaClientId,triggerFacade);
      if (jobFacade.blockedJobsContain(tw.getJobKey())) {
        tw.setState(TriggerState.PAUSED_BLOCKED,terracottaClientId,triggerFacade);
      }
    }
 else     if (jobFacade.blockedJobsContain(tw.getJobKey())) {
      tw.setState(TriggerState.BLOCKED,terracottaClientId,triggerFacade);
    }
 else {
      timeTriggers.add(tw);
    }
    triggerFacade.put(tw.getKey(),tw);
  }
  finally {
    unlock();
  }
}
