@NotNull JavaUiStateImpl resumed(Context context){
  if (context != getContext()) {
    return this;
  }
  Context newContext=getEventProcessor().getContextManager().firstContext();
  if (newContext == null) {
    return new RunningJavaUiState(myDebugSession);
  }
  return new PausedJavaUiState(newContext,myDebugSession);
}
