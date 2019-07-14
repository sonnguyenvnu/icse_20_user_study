@Override public void stateListAnimator(@Nullable StateListAnimator stateListAnimator){
  mPrivateFlags|=PFLAG_STATE_LIST_ANIMATOR_IS_SET;
  getOrCreateObjectProps().append(INDEX_StateListAnimator,stateListAnimator);
}
