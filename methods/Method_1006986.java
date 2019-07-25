@Override public <I,O>SimpleStepBuilder<I,O> chunk(int chunkSize){
  configureWorkerIntegrationFlow();
  return super.chunk(chunkSize);
}
