private void setGuaranteeServiceForElasticJobListeners(final CoordinatorRegistryCenter regCenter,final List<ElasticJobListener> elasticJobListeners){
  GuaranteeService guaranteeService=new GuaranteeService(regCenter,liteJobConfig.getJobName());
  for (  ElasticJobListener each : elasticJobListeners) {
    if (each instanceof AbstractDistributeOnceElasticJobListener) {
      ((AbstractDistributeOnceElasticJobListener)each).setGuaranteeService(guaranteeService);
    }
  }
}
