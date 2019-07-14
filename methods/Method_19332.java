@UiThread public void scrollSmoothToPosition(int position,final int offset,final SmoothScrollAlignmentType type){
  if (mMountedView == null) {
    mCurrentFirstVisiblePosition=position;
    mCurrentOffset=offset;
    mSmoothScrollAlignmentType=type;
    return;
  }
  final int target=type == SmoothScrollAlignmentType.SNAP_TO_CENTER ? position + 1 : position;
  final RecyclerView.SmoothScroller smoothScroller=SnapUtil.getSmoothScrollerWithOffset(mComponentContext.getAndroidContext(),offset,type);
  smoothScroller.setTargetPosition(target);
  mMountedView.getLayoutManager().startSmoothScroll(smoothScroller);
}
