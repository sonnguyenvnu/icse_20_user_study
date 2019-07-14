@Override public void marginPercent(YogaEdge edge,float percent){
  mPrivateFlags|=PFLAG_MARGIN_IS_SET;
  mYogaNode.setMarginPercent(edge,percent);
}
