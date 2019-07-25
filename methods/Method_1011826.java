public void handle(String eventCode){
  if (currentState.hasTransition(eventCode)) {
    transitionTo(currentState.targetState(eventCode));
  }
}
