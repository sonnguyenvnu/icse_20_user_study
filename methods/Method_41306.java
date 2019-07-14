/** 
 * <p> Inform the <code>JobStore</code> that the scheduler has completed the firing of the given <code>Trigger</code> (and the execution its associated <code>Job</code>), and that the <code> {@link org.quartz.JobDataMap}</code> in the given <code>JobDetail</code> should be updated if the <code>Job</code> is stateful. </p>
 */
public void triggeredJobComplete(final OperableTrigger trigger,final JobDetail jobDetail,final CompletedExecutionInstruction triggerInstCode){
  retryExecuteInNonManagedTXLock(LOCK_TRIGGER_ACCESS,new VoidTransactionCallback(){
    public void executeVoid(    Connection conn) throws JobPersistenceException {
      triggeredJobComplete(conn,trigger,jobDetail,triggerInstCode);
    }
  }
);
}
