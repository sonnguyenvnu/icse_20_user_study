@Override public void minHeightPercent(float percent){
  mPrivateFlags|=PFLAG_MIN_HEIGHT_PERCENT_IS_SET;
  getOrCreateFloatProps().append(INDEX_MinHeightPercent,percent);
}
