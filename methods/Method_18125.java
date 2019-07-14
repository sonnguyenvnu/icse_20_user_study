@Override public void maxHeightPx(@Px int maxHeight){
  mPrivateFlags|=PFLAG_MAX_HEIGHT_IS_SET;
  mYogaNode.setMaxHeight(maxHeight);
}
