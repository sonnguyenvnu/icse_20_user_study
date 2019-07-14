/** 
 * <p> Store the given <code> {@link org.quartz.JobDetail}</code> and <code> {@link org.quartz.Trigger}</code>. </p>
 * @param newJob The <code>JobDetail</code> to be stored.
 * @param newTrigger The <code>Trigger</code> to be stored.
 * @throws ObjectAlreadyExistsException if a <code>Job</code> with the same name/group already exists.
 */
public void storeJobAndTrigger(final JobDetail newJob,final OperableTrigger newTrigger) throws JobPersistenceException {
  executeInLock((isLockOnInsert()) ? LOCK_TRIGGER_ACCESS : null,new VoidTransactionCallback(){
    public void executeVoid(    Connection conn) throws JobPersistenceException {
      storeJob(conn,newJob,false);
      storeTrigger(conn,newTrigger,newJob,false,Constants.STATE_WAITING,false,false);
    }
  }
);
}
