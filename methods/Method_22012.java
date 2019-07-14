private void checkConflictJob(final LiteJobConfiguration liteJobConfig){
  Optional<LiteJobConfiguration> liteJobConfigFromZk=find();
  if (liteJobConfigFromZk.isPresent() && !liteJobConfigFromZk.get().getTypeConfig().getJobClass().equals(liteJobConfig.getTypeConfig().getJobClass())) {
    throw new JobConfigurationException("Job conflict with register center. The job '%s' in register center's class is '%s', your job class is '%s'",liteJobConfig.getJobName(),liteJobConfigFromZk.get().getTypeConfig().getJobClass(),liteJobConfig.getTypeConfig().getJobClass());
  }
}
