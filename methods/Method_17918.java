final @Nullable Transition createTransition(ComponentContext c){
  final Transition transition=onCreateTransition(c);
  if (transition != null) {
    TransitionUtils.setOwnerKey(transition,((Component)this).getGlobalKey());
  }
  return transition;
}
