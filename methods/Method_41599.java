/** 
 * <p> Store the given <code> {@link org.quartz.Trigger}</code>. </p>
 * @param newTrigger The <code>Trigger</code> to be stored.
 * @param replaceExisting If <code>true</code>, any <code>Trigger</code> existing in the <code>JobStore</code> with the same name & group should be over-written.
 * @throws ObjectAlreadyExistsException if a <code>Trigger</code> with the same name/group already exists, and replaceExisting is set to false.
 * @see #pauseTriggers(org.quartz.impl.matchers.GroupMatcher)
 */
public void storeTrigger(OperableTrigger newTrigger,boolean replaceExisting) throws JobPersistenceException {
  TriggerWrapper tw=new TriggerWrapper((OperableTrigger)newTrigger.clone());
synchronized (lock) {
    if (triggersByKey.get(tw.key) != null) {
      if (!replaceExisting) {
        throw new ObjectAlreadyExistsException(newTrigger);
      }
      removeTrigger(newTrigger.getKey(),false);
    }
    if (retrieveJob(newTrigger.getJobKey()) == null) {
      throw new JobPersistenceException("The job (" + newTrigger.getJobKey() + ") referenced by the trigger does not exist.");
    }
    List<TriggerWrapper> jobList=triggersByJob.get(tw.jobKey);
    if (jobList == null) {
      jobList=new ArrayList<TriggerWrapper>(1);
      triggersByJob.put(tw.jobKey,jobList);
    }
    jobList.add(tw);
    HashMap<TriggerKey,TriggerWrapper> grpMap=triggersByGroup.get(newTrigger.getKey().getGroup());
    if (grpMap == null) {
      grpMap=new HashMap<TriggerKey,TriggerWrapper>(100);
      triggersByGroup.put(newTrigger.getKey().getGroup(),grpMap);
    }
    grpMap.put(newTrigger.getKey(),tw);
    triggersByKey.put(tw.key,tw);
    if (pausedTriggerGroups.contains(newTrigger.getKey().getGroup()) || pausedJobGroups.contains(newTrigger.getJobKey().getGroup())) {
      tw.state=TriggerWrapper.STATE_PAUSED;
      if (blockedJobs.contains(tw.jobKey)) {
        tw.state=TriggerWrapper.STATE_PAUSED_BLOCKED;
      }
    }
 else     if (blockedJobs.contains(tw.jobKey)) {
      tw.state=TriggerWrapper.STATE_BLOCKED;
    }
 else {
      timeTriggers.add(tw);
    }
  }
}
