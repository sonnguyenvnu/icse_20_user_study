@Nullable public static Transition mergeTransitions(@NonNull Transition... transitions){
  int count=0;
  int nonNullIndex=-1;
  for (int i=0; i < transitions.length; i++) {
    if (transitions[i] != null) {
      count++;
      nonNullIndex=i;
    }
  }
  if (count == 0) {
    return null;
  }
  if (count == 1) {
    return transitions[nonNullIndex];
  }
  TransitionSet transitionSet=new TransitionSet();
  for (int i=0; i < transitions.length; i++) {
    if (transitions[i] != null) {
      transitionSet.addTransition(transitions[i]);
    }
  }
  return transitionSet;
}
