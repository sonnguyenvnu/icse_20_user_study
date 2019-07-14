@Override public void maxWidthPx(@Px int maxWidth){
  mPrivateFlags|=PFLAG_MAX_WIDTH_IS_SET;
  mYogaNode.setMaxWidth(maxWidth);
}
