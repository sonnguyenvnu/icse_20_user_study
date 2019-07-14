private boolean shouldCreateNewStorage(){
  State currentState=mCurrentState;
  return (currentState.delegate == null || currentState.rootDirectory == null || !currentState.rootDirectory.exists());
}
