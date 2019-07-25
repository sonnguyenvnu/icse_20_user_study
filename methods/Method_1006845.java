/** 
 * Take the  {@link JobFactory} provided and register it with the{@link JobRegistry}.
 * @param jobFactory a {@link JobFactory}
 * @param params not needed by this listener.
 * @throws Exception if there is a problem
 */
public void bind(JobFactory jobFactory,Map<String,?> params) throws Exception {
  logger.info("Binding JobFactory: " + jobFactory.getJobName());
  jobRegistry.register(jobFactory);
}
