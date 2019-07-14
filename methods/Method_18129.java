@Override public void minWidthPercent(float percent){
  mPrivateFlags|=PFLAG_MIN_WIDTH_IS_SET;
  mYogaNode.setMinWidthPercent(percent);
}
