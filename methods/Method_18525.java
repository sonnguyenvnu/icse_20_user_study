@Override public void transitionKeyType(@Nullable Transition.TransitionKeyType type){
  mPrivateFlags|=PFLAG_TRANSITION_KEY_TYPE_IS_SET;
  getOrCreateObjectProps().append(INDEX_TransitionKeyType,type);
}
