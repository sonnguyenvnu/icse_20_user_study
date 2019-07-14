@Override public InternalNode stateListAnimatorRes(@DrawableRes int resId){
  mPrivateFlags|=PFLAG_STATE_LIST_ANIMATOR_RES_SET;
  mStateListAnimatorRes=resId;
  wrapInView();
  return this;
}
