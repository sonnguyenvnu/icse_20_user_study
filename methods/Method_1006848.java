@Override public void register(String jobName,Collection<Step> steps) throws DuplicateJobException {
  Assert.notNull(jobName,"The job name cannot be null.");
  Assert.notNull(steps,"The job steps cannot be null.");
  final Map<String,Step> jobSteps=new HashMap<>();
  for (  Step step : steps) {
    jobSteps.put(step.getName(),step);
  }
  final Object previousValue=map.putIfAbsent(jobName,jobSteps);
  if (previousValue != null) {
    throw new DuplicateJobException("A job configuration with this name [" + jobName + "] was already registered");
  }
}
