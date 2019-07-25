private void resume(Context suspendContext){
  JavaUiStateImpl state=getUiState();
  setState(state,state.resumed(suspendContext),false);
}
