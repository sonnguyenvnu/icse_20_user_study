@Override public <I,O>SimpleStepBuilder<I,O> chunk(CompletionPolicy completionPolicy){
  configureWorkerIntegrationFlow();
  return super.chunk(completionPolicy);
}
