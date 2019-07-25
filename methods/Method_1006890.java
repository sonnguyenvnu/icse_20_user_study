/** 
 * Register additional listener.
 * @param jobExecutionListener instance {@link JobExecutionListener} to be registered.
 */
public void register(JobExecutionListener jobExecutionListener){
  listeners.add(jobExecutionListener);
}
