/** 
 * Take the  {@link JobFactory} provided and unregister it with the{@link JobRegistry}.
 * @param jobFactory a {@link JobFactory}
 * @param params not needed by this listener.
 * @throws Exception if there is a problem
 */
public void unbind(JobFactory jobFactory,Map<String,?> params) throws Exception {
  logger.info("Unbinding JobFactory: " + jobFactory.getJobName());
  jobRegistry.unregister(jobFactory.getJobName());
}
