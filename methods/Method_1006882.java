/** 
 * Run the provided job with the given  {@link JobParameters}. The {@link JobParameters} will be used to determine if this is an executionof an existing job instance, or if a new one should be created.
 * @param job the job to be run.
 * @param jobParameters the {@link JobParameters} for this particularexecution.
 * @return the {@link JobExecution} if it returns synchronously. If theimplementation is asynchronous, the status might well be unknown.
 * @throws JobExecutionAlreadyRunningException if the JobInstance alreadyexists and has an execution already running.
 * @throws JobRestartException if the execution would be a re-start, but are-start is either not allowed or not needed.
 * @throws JobInstanceAlreadyCompleteException if this instance has alreadycompleted successfully
 * @throws JobParametersInvalidException thrown if jobParameters is invalid.
 */
@Override public JobExecution run(final Job job,final JobParameters jobParameters) throws JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException, JobParametersInvalidException {
  Assert.notNull(job,"The Job must not be null.");
  Assert.notNull(jobParameters,"The JobParameters must not be null.");
  final JobExecution jobExecution;
  JobExecution lastExecution=jobRepository.getLastJobExecution(job.getName(),jobParameters);
  if (lastExecution != null) {
    if (!job.isRestartable()) {
      throw new JobRestartException("JobInstance already exists and is not restartable");
    }
    for (    StepExecution execution : lastExecution.getStepExecutions()) {
      BatchStatus status=execution.getStatus();
      if (status.isRunning() || status == BatchStatus.STOPPING) {
        throw new JobExecutionAlreadyRunningException("A job execution for this job is already running: " + lastExecution);
      }
 else       if (status == BatchStatus.UNKNOWN) {
        throw new JobRestartException("Cannot restart step [" + execution.getStepName() + "] from UNKNOWN status. " + "The last execution ended with a failure that could not be rolled back, " + "so it may be dangerous to proceed. Manual intervention is probably necessary.");
      }
    }
  }
  job.getJobParametersValidator().validate(jobParameters);
  jobExecution=jobRepository.createJobExecution(job.getName(),jobParameters);
  try {
    taskExecutor.execute(new Runnable(){
      @Override public void run(){
        try {
          logger.info("Job: [" + job + "] launched with the following parameters: [" + jobParameters + "]");
          job.execute(jobExecution);
          Duration jobExecutionDuration=BatchMetrics.calculateDuration(jobExecution.getStartTime(),jobExecution.getEndTime());
          logger.info("Job: [" + job + "] completed with the following parameters: [" + jobParameters + "] and the following status: [" + jobExecution.getStatus() + "]" + (jobExecutionDuration == null ? "" : " in " + BatchMetrics.formatDuration(jobExecutionDuration)));
        }
 catch (        Throwable t) {
          logger.info("Job: [" + job + "] failed unexpectedly and fatally with the following parameters: [" + jobParameters + "]",t);
          rethrow(t);
        }
      }
      private void rethrow(      Throwable t){
        if (t instanceof RuntimeException) {
          throw (RuntimeException)t;
        }
 else         if (t instanceof Error) {
          throw (Error)t;
        }
        throw new IllegalStateException(t);
      }
    }
);
  }
 catch (  TaskRejectedException e) {
    jobExecution.upgradeStatus(BatchStatus.FAILED);
    if (jobExecution.getExitStatus().equals(ExitStatus.UNKNOWN)) {
      jobExecution.setExitStatus(ExitStatus.FAILED.addExitDescription(e));
    }
    jobRepository.update(jobExecution);
  }
  return jobExecution;
}
