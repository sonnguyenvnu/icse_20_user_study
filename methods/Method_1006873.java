@Override public void abandon(long jobExecutionId) throws NoSuchJobExecutionException, JobExecutionIsRunningException, JobSecurityException {
  org.springframework.batch.core.JobExecution jobExecution=jobExplorer.getJobExecution(jobExecutionId);
  if (jobExecution == null) {
    throw new NoSuchJobExecutionException("Unable to retrieve JobExecution for id " + jobExecutionId);
  }
  if (jobExecution.isRunning()) {
    throw new JobExecutionIsRunningException("Unable to abandon a job that is currently running");
  }
  jobExecution.upgradeStatus(BatchStatus.ABANDONED);
  jobRepository.update(jobExecution);
}
