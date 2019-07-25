@Override public void execute(ExecutionRequest request){
  TestDescriptor engine=request.getRootTestDescriptor();
  EngineExecutionListener listener=request.getEngineExecutionListener();
  listener.executionStarted(engine);
  for (  TestDescriptor child : engine.getChildren()) {
    listener.executionStarted(child);
    listener.executionFinished(child,TestExecutionResult.successful());
  }
  listener.executionFinished(engine,TestExecutionResult.successful());
}
