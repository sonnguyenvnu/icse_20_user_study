private void step(EventsProcessor.StepKind kind){
  JavaUiStateImpl state=getUiState();
  Context context=state.getContext();
  assert context != null : "Context is null while debug session state is " + myExecutionState;
  myEventsProcessor.step(kind,context);
}
