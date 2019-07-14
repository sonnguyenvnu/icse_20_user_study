@Override public InternalNode stateListAnimator(@Nullable StateListAnimator stateListAnimator){
  mPrivateFlags|=PFLAG_STATE_LIST_ANIMATOR_SET;
  mStateListAnimator=stateListAnimator;
  wrapInView();
  return this;
}
