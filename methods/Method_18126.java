@Override public void maxWidthPercent(float percent){
  mPrivateFlags|=PFLAG_MAX_WIDTH_IS_SET;
  mYogaNode.setMaxWidthPercent(percent);
}
