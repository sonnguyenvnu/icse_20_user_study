public synchronized boolean hasCompletedLatestLayout(){
  return mRenderInfo.rendersView() || (mComponentTree != null && mComponentTree.hasCompatibleLayout(mLastRequestedWidthSpec,mLastRequestedHeightSpec));
}
