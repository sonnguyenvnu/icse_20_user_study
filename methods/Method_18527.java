@Override public void stateListAnimatorRes(@DrawableRes int resId){
  mPrivateFlags|=PFLAG_STATE_LIST_ANIMATOR_RES_IS_SET;
  getOrCreateIntProps().append(INDEX_StateListAnimatorRes,resId);
}
