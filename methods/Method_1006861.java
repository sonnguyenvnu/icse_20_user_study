protected void enhance(Job target){
  if (target instanceof AbstractJob) {
    AbstractJob job=(AbstractJob)target;
    job.setJobRepository(properties.getJobRepository());
    JobParametersIncrementer jobParametersIncrementer=properties.getJobParametersIncrementer();
    if (jobParametersIncrementer != null) {
      job.setJobParametersIncrementer(jobParametersIncrementer);
    }
    JobParametersValidator jobParametersValidator=properties.getJobParametersValidator();
    if (jobParametersValidator != null) {
      job.setJobParametersValidator(jobParametersValidator);
    }
    Boolean restartable=properties.getRestartable();
    if (restartable != null) {
      job.setRestartable(restartable);
    }
    List<JobExecutionListener> listeners=properties.getJobExecutionListeners();
    if (!listeners.isEmpty()) {
      job.setJobExecutionListeners(listeners.toArray(new JobExecutionListener[0]));
    }
  }
}
