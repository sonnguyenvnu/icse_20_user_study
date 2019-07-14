@Override public InternalNode transitionKeyType(@Nullable Transition.TransitionKeyType type){
  mPrivateFlags|=PFLAG_TRANSITION_KEY_TYPE_IS_SET;
  mTransitionKeyType=type;
  return this;
}
