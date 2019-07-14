/** 
 * <p> Store the given <code> {@link org.quartz.JobDetail}</code>. </p>
 * @param newJob The <code>JobDetail</code> to be stored.
 * @param replaceExisting If <code>true</code>, any <code>Job</code> existing in the <code>JobStore</code> with the same name & group should be over-written.
 * @throws ObjectAlreadyExistsException if a <code>Job</code> with the same name/group already exists, and replaceExisting is set to false.
 */
public void storeJob(final JobDetail newJob,final boolean replaceExisting) throws JobPersistenceException {
  executeInLock((isLockOnInsert() || replaceExisting) ? LOCK_TRIGGER_ACCESS : null,new VoidTransactionCallback(){
    public void executeVoid(    Connection conn) throws JobPersistenceException {
      storeJob(conn,newJob,replaceExisting);
    }
  }
);
}
