/** 
 * <p> Inform the <code>JobStore</code> that the scheduler no longer plans to fire the given <code>Trigger</code>, that it had previously acquired (reserved). </p>
 */
public void releaseAcquiredTrigger(final OperableTrigger trigger){
  retryExecuteInNonManagedTXLock(LOCK_TRIGGER_ACCESS,new VoidTransactionCallback(){
    public void executeVoid(    Connection conn) throws JobPersistenceException {
      releaseAcquiredTrigger(conn,trigger);
    }
  }
);
}
