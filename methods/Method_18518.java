@Override public void maxHeightPx(@Px int maxHeight){
  mPrivateFlags|=PFLAG_MAX_HEIGHT_IS_SET;
  getOrCreateIntProps().append(INDEX_MaxHeightPx,maxHeight);
}
