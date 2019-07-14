@Override public void minWidthPercent(float percent){
  mPrivateFlags|=PFLAG_MIN_WIDTH_PERCENT_IS_SET;
  getOrCreateFloatProps().append(INDEX_MinWidthPercent,percent);
}
