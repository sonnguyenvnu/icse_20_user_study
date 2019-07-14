public DialogTransition getTransitionType(){
  return transitionType == null ? DialogTransition.CENTER : transitionType.get();
}
