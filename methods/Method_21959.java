private JobDetail createJobDetail(final String jobClass){
  JobDetail result=JobBuilder.newJob(LiteJob.class).withIdentity(liteJobConfig.getJobName()).build();
  result.getJobDataMap().put(JOB_FACADE_DATA_MAP_KEY,jobFacade);
  Optional<ElasticJob> elasticJobInstance=createElasticJobInstance();
  if (elasticJobInstance.isPresent()) {
    result.getJobDataMap().put(ELASTIC_JOB_DATA_MAP_KEY,elasticJobInstance.get());
  }
 else   if (!jobClass.equals(ScriptJob.class.getCanonicalName())) {
    try {
      result.getJobDataMap().put(ELASTIC_JOB_DATA_MAP_KEY,Class.forName(jobClass).newInstance());
    }
 catch (    final ReflectiveOperationException ex) {
      throw new JobConfigurationException("Elastic-Job: Job class '%s' can not initialize.",jobClass);
    }
  }
  return result;
}
