@Override public JobStepBuilder job(Job job){
  configureWorkerIntegrationFlow();
  return super.job(job);
}
