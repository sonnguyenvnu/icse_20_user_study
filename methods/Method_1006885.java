@Override public JobExecution abandon(long jobExecutionId) throws NoSuchJobExecutionException, JobExecutionAlreadyRunningException {
  JobExecution jobExecution=findExecutionById(jobExecutionId);
  if (jobExecution.getStatus().isLessThan(BatchStatus.STOPPING)) {
    throw new JobExecutionAlreadyRunningException("JobExecution is running or complete and therefore cannot be aborted");
  }
  logger.info("Aborting job execution: " + jobExecution);
  jobExecution.upgradeStatus(BatchStatus.ABANDONED);
  jobExecution.setEndTime(new Date());
  jobRepository.update(jobExecution);
  return jobExecution;
}
