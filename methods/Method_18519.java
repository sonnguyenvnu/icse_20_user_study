@Override public void maxHeightPercent(float percent){
  mPrivateFlags|=PFLAG_MAX_HEIGHT_PERCENT_IS_SET;
  getOrCreateFloatProps().append(INDEX_MaxHeightPercent,percent);
}
