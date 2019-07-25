@Override public TaskletStepBuilder tasklet(Tasklet tasklet){
  configureWorkerIntegrationFlow();
  return super.tasklet(tasklet);
}
