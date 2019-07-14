@Override public void marginAuto(YogaEdge edge){
  mPrivateFlags|=PFLAG_MARGIN_IS_SET;
  mYogaNode.setMarginAuto(edge);
}
