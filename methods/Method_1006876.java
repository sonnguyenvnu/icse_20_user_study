/** 
 * Build the step from the components collected by the fluent setters. Delegates first to  {@link #enhance(Step)} andthen to  {@link #createTasklet()} in subclasses to create the actual tasklet.
 * @return a tasklet step fully configured and read to execute
 */
@Override public TaskletStep build(){
  registerStepListenerAsChunkListener();
  BatchletStep step=new BatchletStep(getName(),batchPropertyContext);
  super.enhance(step);
  step.setChunkListeners(chunkListeners.toArray(new ChunkListener[0]));
  if (getTransactionAttribute() != null) {
    step.setTransactionAttribute(getTransactionAttribute());
  }
  if (getStepOperations() == null) {
    stepOperations(new RepeatTemplate());
    if (getTaskExecutor() != null) {
      TaskExecutorRepeatTemplate repeatTemplate=new TaskExecutorRepeatTemplate();
      repeatTemplate.setTaskExecutor(getTaskExecutor());
      repeatTemplate.setThrottleLimit(getThrottleLimit());
      stepOperations(repeatTemplate);
    }
    ((RepeatTemplate)getStepOperations()).setExceptionHandler(getExceptionHandler());
  }
  step.setStepOperations(getStepOperations());
  step.setTasklet(createTasklet());
  step.setStreams(getStreams().toArray(new ItemStream[0]));
  try {
    step.afterPropertiesSet();
  }
 catch (  Exception e) {
    throw new StepBuilderException(e);
  }
  return step;
}
