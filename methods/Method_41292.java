/** 
 * Determines if a Trigger for the given job should be blocked.   State can only transition to STATE_PAUSED_BLOCKED/BLOCKED from  PAUSED/STATE_WAITING respectively.
 * @return STATE_PAUSED_BLOCKED, BLOCKED, or the currentState. 
 */
protected String checkBlockedState(Connection conn,JobKey jobKey,String currentState) throws JobPersistenceException {
  if ((!currentState.equals(STATE_WAITING)) && (!currentState.equals(STATE_PAUSED))) {
    return currentState;
  }
  try {
    List<FiredTriggerRecord> lst=getDelegate().selectFiredTriggerRecordsByJob(conn,jobKey.getName(),jobKey.getGroup());
    if (lst.size() > 0) {
      FiredTriggerRecord rec=lst.get(0);
      if (rec.isJobDisallowsConcurrentExecution()) {
        return (STATE_PAUSED.equals(currentState)) ? STATE_PAUSED_BLOCKED : STATE_BLOCKED;
      }
    }
    return currentState;
  }
 catch (  SQLException e) {
    throw new JobPersistenceException("Couldn't determine if trigger should be in a blocked state '" + jobKey + "': " + e.getMessage(),e);
  }
}
