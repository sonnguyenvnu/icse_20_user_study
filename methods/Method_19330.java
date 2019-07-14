/** 
 * Call from the owning  {@link Component}'s onUnmount. This is where the adapter is removed from the  {@link RecyclerView}.
 * @param view the {@link RecyclerView} being unmounted.
 */
@UiThread @Override public void unmount(RecyclerView view){
  ThreadUtils.assertMainThread();
  final LayoutManager layoutManager=mLayoutInfo.getLayoutManager();
  final View firstView=layoutManager.findViewByPosition(mCurrentFirstVisiblePosition);
  if (firstView != null) {
    final boolean reverseLayout=getReverseLayout();
    if (mLayoutInfo.getScrollDirection() == HORIZONTAL) {
      mCurrentOffset=reverseLayout ? view.getWidth() - layoutManager.getPaddingRight() - layoutManager.getDecoratedRight(firstView) : layoutManager.getDecoratedLeft(firstView) - layoutManager.getPaddingLeft();
    }
 else {
      mCurrentOffset=reverseLayout ? view.getHeight() - layoutManager.getPaddingBottom() - layoutManager.getDecoratedBottom(firstView) : layoutManager.getDecoratedTop(firstView) - layoutManager.getPaddingTop();
    }
  }
 else {
    mCurrentOffset=0;
  }
  view.removeOnScrollListener(mViewportManager.getScrollListener());
  if (view instanceof HasPostDispatchDrawListener) {
    ((HasPostDispatchDrawListener)view).setPostDispatchDrawListener(null);
  }
 else   if (view.getViewTreeObserver() != null) {
    view.getViewTreeObserver().removeOnPreDrawListener(mOnPreDrawListener);
  }
  maybeDispatchDataRendered();
  view.setAdapter(null);
  view.setLayoutManager(null);
  mViewportManager.removeViewportChangedListener(mViewportChangedListener);
  if (mMountedView != view) {
    return;
  }
  mMountedView=null;
  if (mStickyHeaderController != null) {
    mStickyHeaderController.reset();
  }
  mLayoutInfo.setRenderInfoCollection(null);
}
