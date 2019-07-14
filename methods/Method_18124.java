@Override public void maxHeightPercent(float percent){
  mPrivateFlags|=PFLAG_MAX_HEIGHT_IS_SET;
  mYogaNode.setMaxHeightPercent(percent);
}
