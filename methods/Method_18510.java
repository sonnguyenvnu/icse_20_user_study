@Override public void widthPercent(float percent){
  mPrivateFlags|=PFLAG_WIDTH_PERCENT_IS_SET;
  getOrCreateFloatProps().append(INDEX_WidthPercent,percent);
}
