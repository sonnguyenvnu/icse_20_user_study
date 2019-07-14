@Override public void alignSelf(YogaAlign alignSelf){
  mPrivateFlags|=PFLAG_ALIGN_SELF_IS_SET;
  mYogaNode.setAlignSelf(alignSelf);
}
