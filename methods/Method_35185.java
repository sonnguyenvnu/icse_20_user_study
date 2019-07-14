@NonNull public static TransitionSet mergeTransitions(int ordering,Transition... transitions){
  TransitionSet transitionSet=new TransitionSet();
  for (  Transition transition : transitions) {
    if (transition != null) {
      transitionSet.addTransition(transition);
    }
  }
  transitionSet.setOrdering(ordering);
  return transitionSet;
}
