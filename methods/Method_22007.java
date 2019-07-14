/** 
 * ???????.
 * @param elasticJob ???????
 * @param jobFacade ??????????
 * @return ?????
 */
@SuppressWarnings("unchecked") public static AbstractElasticJobExecutor getJobExecutor(final ElasticJob elasticJob,final JobFacade jobFacade){
  if (null == elasticJob) {
    return new ScriptJobExecutor(jobFacade);
  }
  if (elasticJob instanceof SimpleJob) {
    return new SimpleJobExecutor((SimpleJob)elasticJob,jobFacade);
  }
  if (elasticJob instanceof DataflowJob) {
    return new DataflowJobExecutor((DataflowJob)elasticJob,jobFacade);
  }
  throw new JobConfigurationException("Cannot support job type '%s'",elasticJob.getClass().getCanonicalName());
}
