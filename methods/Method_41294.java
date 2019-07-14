/** 
 * <p> Resume (un-pause) the <code> {@link org.quartz.Job}</code> with the given name. </p> <p> If any of the <code>Job</code>'s<code>Trigger</code> s missed one or more fire-times, then the <code>Trigger</code>'s misfire instruction will be applied. </p>
 * @see #pauseJob(JobKey)
 */
public void resumeJob(final JobKey jobKey) throws JobPersistenceException {
  executeInLock(LOCK_TRIGGER_ACCESS,new VoidTransactionCallback(){
    public void executeVoid(    Connection conn) throws JobPersistenceException {
      List<OperableTrigger> triggers=getTriggersForJob(conn,jobKey);
      for (      OperableTrigger trigger : triggers) {
        resumeTrigger(conn,trigger.getKey());
      }
    }
  }
);
}
