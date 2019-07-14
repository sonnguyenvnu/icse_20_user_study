/** 
 * <p> Will recover any failed or misfired jobs and clean up the data store as appropriate. </p>
 * @throws JobPersistenceException if jobs could not be recovered
 */
protected void recoverJobs(Connection conn) throws JobPersistenceException {
  try {
    int rows=getDelegate().updateTriggerStatesFromOtherStates(conn,STATE_WAITING,STATE_ACQUIRED,STATE_BLOCKED);
    rows+=getDelegate().updateTriggerStatesFromOtherStates(conn,STATE_PAUSED,STATE_PAUSED_BLOCKED,STATE_PAUSED_BLOCKED);
    getLog().info("Freed " + rows + " triggers from 'acquired' / 'blocked' state.");
    recoverMisfiredJobs(conn,true);
    List<OperableTrigger> recoveringJobTriggers=getDelegate().selectTriggersForRecoveringJobs(conn);
    getLog().info("Recovering " + recoveringJobTriggers.size() + " jobs that were in-progress at the time of the last shut-down.");
    for (    OperableTrigger recoveringJobTrigger : recoveringJobTriggers) {
      if (jobExists(conn,recoveringJobTrigger.getJobKey())) {
        recoveringJobTrigger.computeFirstFireTime(null);
        storeTrigger(conn,recoveringJobTrigger,null,false,STATE_WAITING,false,true);
      }
    }
    getLog().info("Recovery complete.");
    List<TriggerKey> cts=getDelegate().selectTriggersInState(conn,STATE_COMPLETE);
    for (    TriggerKey ct : cts) {
      removeTrigger(conn,ct);
    }
    getLog().info("Removed " + cts.size() + " 'complete' triggers.");
    int n=getDelegate().deleteFiredTriggers(conn);
    getLog().info("Removed " + n + " stale fired job entries.");
  }
 catch (  JobPersistenceException e) {
    throw e;
  }
catch (  Exception e) {
    throw new JobPersistenceException("Couldn't recover jobs: " + e.getMessage(),e);
  }
}
