@Override public void copyInto(ComponentContext c,InternalNode node){
  if (node == NULL_LAYOUT) {
    return;
  }
  c.applyStyle(node,mDefStyleAttr,mDefStyleRes);
  if (mNodeInfo != null) {
    mNodeInfo.copyInto(node.getOrCreateNodeInfo());
  }
  if ((mPrivateFlags & PFLAG_BACKGROUND_IS_SET) != 0L) {
    node.background(mBackground);
  }
  if ((mPrivateFlags & PFLAG_TEST_KEY_IS_SET) != 0L) {
    node.testKey(mTestKey);
  }
  if (mWrapInView) {
    node.wrapInView();
  }
  if (mLayoutProps != null) {
    mLayoutProps.copyInto(node);
  }
  if (mOtherProps != null) {
    mOtherProps.copyInto(node);
  }
}
