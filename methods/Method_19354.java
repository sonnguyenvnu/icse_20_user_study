private @Nullable ComponentTreeHolderRangeInfo getHolderForRangeInfo(){
  ComponentTreeHolderRangeInfo holderForRangeInfo=null;
  if (!mComponentTreeHolders.isEmpty()) {
    final int positionToComputeLayout=findInitialComponentPosition(mComponentTreeHolders,mTraverseLayoutBackwards);
    if (mCurrentFirstVisiblePosition < mComponentTreeHolders.size() && positionToComputeLayout >= 0) {
      holderForRangeInfo=new ComponentTreeHolderRangeInfo(positionToComputeLayout,mComponentTreeHolders);
    }
  }
 else   if (!mAsyncComponentTreeHolders.isEmpty()) {
    final int positionToComputeLayout=findInitialComponentPosition(mAsyncComponentTreeHolders,mTraverseLayoutBackwards);
    if (positionToComputeLayout >= 0) {
      holderForRangeInfo=new ComponentTreeHolderRangeInfo(positionToComputeLayout,mAsyncComponentTreeHolders);
    }
  }
  return holderForRangeInfo;
}
