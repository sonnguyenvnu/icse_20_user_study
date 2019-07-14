@Override public void maxWidthPercent(float percent){
  mPrivateFlags|=PFLAG_MAX_WIDTH_PERCENT_IS_SET;
  getOrCreateFloatProps().append(INDEX_MaxWidthPercent,percent);
}
