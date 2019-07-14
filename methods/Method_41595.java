/** 
 * <p> Store the given <code> {@link org.quartz.JobDetail}</code> and <code> {@link org.quartz.Trigger}</code>. </p>
 * @param newJob The <code>JobDetail</code> to be stored.
 * @param newTrigger The <code>Trigger</code> to be stored.
 * @throws ObjectAlreadyExistsException if a <code>Job</code> with the same name/group already exists.
 */
public void storeJobAndTrigger(JobDetail newJob,OperableTrigger newTrigger) throws JobPersistenceException {
  storeJob(newJob,false);
  storeTrigger(newTrigger,false);
}
