@Override public void heightPercent(float percent){
  mPrivateFlags|=PFLAG_HEIGHT_PERCENT_IS_SET;
  getOrCreateFloatProps().append(INDEX_HeightPercentage,percent);
}
