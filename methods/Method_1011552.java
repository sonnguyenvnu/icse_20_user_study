private void pause(@NotNull Context suspendContext){
  JavaUiStateImpl state=getUiState();
  setState(state,state.paused(suspendContext),false);
}
