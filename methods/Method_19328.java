/** 
 * Call from the owning  {@link Component}'s onMount. This is where the adapter is assigned to the {@link RecyclerView}.
 * @param view the {@link RecyclerView} being mounted.
 */
@UiThread @Override public void mount(RecyclerView view){
  ThreadUtils.assertMainThread();
  if (mMountedView == view) {
    return;
  }
  if (mMountedView != null) {
    unmount(mMountedView);
  }
  mMountedView=view;
  mIsInitMounted=true;
  final LayoutManager layoutManager=mLayoutInfo.getLayoutManager();
  layoutManager.setItemPrefetchEnabled(false);
  view.getPaddingLeft();
  view.setLayoutManager(layoutManager);
  view.setAdapter(mInternalAdapter);
  view.addOnScrollListener(mViewportManager.getScrollListener());
  if (view instanceof HasPostDispatchDrawListener) {
    ((HasPostDispatchDrawListener)view).setPostDispatchDrawListener(mPostDispatchDrawListener);
  }
 else   if (view.getViewTreeObserver() != null) {
    view.getViewTreeObserver().addOnPreDrawListener(mOnPreDrawListener);
  }
  mLayoutInfo.setRenderInfoCollection(this);
  mViewportManager.addViewportChangedListener(mViewportChangedListener);
  if (mCurrentFirstVisiblePosition != RecyclerView.NO_POSITION && mCurrentFirstVisiblePosition >= 0 && !mIsCircular) {
    if (mSmoothScrollAlignmentType != null) {
      scrollSmoothToPosition(mCurrentFirstVisiblePosition,mCurrentOffset,mSmoothScrollAlignmentType);
    }
 else {
      if (layoutManager instanceof LinearLayoutManager) {
        ((LinearLayoutManager)layoutManager).scrollToPositionWithOffset(mCurrentFirstVisiblePosition,mCurrentOffset);
      }
 else {
        view.scrollToPosition(mCurrentFirstVisiblePosition);
      }
    }
  }
 else   if (mIsCircular) {
    final int jumpToMiddle=Integer.MAX_VALUE / 2;
    final int offsetFirstItem=mComponentTreeHolders.isEmpty() ? 0 : jumpToMiddle % mComponentTreeHolders.size();
    view.scrollToPosition(jumpToMiddle - offsetFirstItem + (mCurrentFirstVisiblePosition != RecyclerView.NO_POSITION && mCurrentFirstVisiblePosition >= 0 ? mCurrentFirstVisiblePosition : 0));
  }
  enableStickyHeader(mMountedView);
}
