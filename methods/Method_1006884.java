@Override public Long start(String jobName,String parameters) throws NoSuchJobException, JobInstanceAlreadyExistsException, JobParametersInvalidException {
  logger.info("Checking status of job with name=" + jobName);
  JobParameters jobParameters=jobParametersConverter.getJobParameters(PropertiesConverter.stringToProperties(parameters));
  if (jobRepository.isJobInstanceExists(jobName,jobParameters)) {
    throw new JobInstanceAlreadyExistsException(String.format("Cannot start a job instance that already exists with name=%s and parameters=%s",jobName,parameters));
  }
  Job job=jobRegistry.getJob(jobName);
  logger.info(String.format("Attempting to launch job with name=%s and parameters=%s",jobName,parameters));
  try {
    return jobLauncher.run(job,jobParameters).getId();
  }
 catch (  JobExecutionAlreadyRunningException e) {
    throw new UnexpectedJobExecutionException(String.format(ILLEGAL_STATE_MSG,"job execution already running",jobName,parameters),e);
  }
catch (  JobRestartException e) {
    throw new UnexpectedJobExecutionException(String.format(ILLEGAL_STATE_MSG,"job not restartable",jobName,parameters),e);
  }
catch (  JobInstanceAlreadyCompleteException e) {
    throw new UnexpectedJobExecutionException(String.format(ILLEGAL_STATE_MSG,"job already complete",jobName,parameters),e);
  }
}
