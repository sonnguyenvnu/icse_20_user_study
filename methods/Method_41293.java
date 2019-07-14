/** 
 * <p> Resume (un-pause) the <code> {@link org.quartz.Trigger}</code> with the given name. </p> <p> If the <code>Trigger</code> missed one or more fire-times, then the <code>Trigger</code>'s misfire instruction will be applied. </p>
 * @see #pauseTrigger(TriggerKey)
 */
public void resumeTrigger(final TriggerKey triggerKey) throws JobPersistenceException {
  executeInLock(LOCK_TRIGGER_ACCESS,new VoidTransactionCallback(){
    public void executeVoid(    Connection conn) throws JobPersistenceException {
      resumeTrigger(conn,triggerKey);
    }
  }
);
}
