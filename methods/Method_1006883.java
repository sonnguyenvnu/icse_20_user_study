@Override public Long restart(long executionId) throws JobInstanceAlreadyCompleteException, NoSuchJobExecutionException, NoSuchJobException, JobRestartException, JobParametersInvalidException {
  logger.info("Checking status of job execution with id=" + executionId);
  JobExecution jobExecution=findExecutionById(executionId);
  String jobName=jobExecution.getJobInstance().getJobName();
  Job job=jobRegistry.getJob(jobName);
  JobParameters parameters=jobExecution.getJobParameters();
  logger.info(String.format("Attempting to resume job with name=%s and parameters=%s",jobName,parameters));
  try {
    return jobLauncher.run(job,parameters).getId();
  }
 catch (  JobExecutionAlreadyRunningException e) {
    throw new UnexpectedJobExecutionException(String.format(ILLEGAL_STATE_MSG,"job execution already running",jobName,parameters),e);
  }
}
