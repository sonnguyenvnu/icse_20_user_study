/** 
 * <p> Insert or update a trigger. </p>
 */
@SuppressWarnings("ConstantConditions") protected void storeTrigger(Connection conn,OperableTrigger newTrigger,JobDetail job,boolean replaceExisting,String state,boolean forceState,boolean recovering) throws JobPersistenceException {
  boolean existingTrigger=triggerExists(conn,newTrigger.getKey());
  if ((existingTrigger) && (!replaceExisting)) {
    throw new ObjectAlreadyExistsException(newTrigger);
  }
  try {
    boolean shouldBepaused;
    if (!forceState) {
      shouldBepaused=getDelegate().isTriggerGroupPaused(conn,newTrigger.getKey().getGroup());
      if (!shouldBepaused) {
        shouldBepaused=getDelegate().isTriggerGroupPaused(conn,ALL_GROUPS_PAUSED);
        if (shouldBepaused) {
          getDelegate().insertPausedTriggerGroup(conn,newTrigger.getKey().getGroup());
        }
      }
      if (shouldBepaused && (state.equals(STATE_WAITING) || state.equals(STATE_ACQUIRED))) {
        state=STATE_PAUSED;
      }
    }
    if (job == null) {
      job=retrieveJob(conn,newTrigger.getJobKey());
    }
    if (job == null) {
      throw new JobPersistenceException("The job (" + newTrigger.getJobKey() + ") referenced by the trigger does not exist.");
    }
    if (job.isConcurrentExectionDisallowed() && !recovering) {
      state=checkBlockedState(conn,job.getKey(),state);
    }
    if (existingTrigger) {
      getDelegate().updateTrigger(conn,newTrigger,state,job);
    }
 else {
      getDelegate().insertTrigger(conn,newTrigger,state,job);
    }
  }
 catch (  Exception e) {
    throw new JobPersistenceException("Couldn't store trigger '" + newTrigger.getKey() + "' for '" + newTrigger.getJobKey() + "' job:" + e.getMessage(),e);
  }
}
