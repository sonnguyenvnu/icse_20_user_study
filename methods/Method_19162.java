/** 
 * @param animated whether the scroll will happen with animation.
 * @param defaultTarget target to use as fallback.
 * @param snapTarget target that takes into account snapping behavior.
 * @param smoothScroller custom smooth scroller
 */
private void requestScrollToPositionInner(boolean animated,int defaultTarget,int snapTarget,RecyclerView.SmoothScroller smoothScroller){
  final RecyclerView recyclerView=getRecyclerView();
  if (recyclerView == null) {
    return;
  }
  final RecyclerView.LayoutManager layoutManager=recyclerView.getLayoutManager();
  if (layoutManager == null || recyclerView.isLayoutFrozen()) {
    return;
  }
  if (!animated) {
    requestScrollToPosition(defaultTarget,false);
    return;
  }
  if (smoothScroller == null && mSnapMode == SNAP_NONE) {
    requestScrollToPosition(defaultTarget,true);
    return;
  }
  if (smoothScroller == null) {
    smoothScroller=SnapUtil.getSmoothScrollerWithOffset(recyclerView.getContext(),0,getSmoothScrollAlignmentTypeFrom(mSnapMode));
  }
  smoothScroller.setTargetPosition(snapTarget);
  layoutManager.startSmoothScroll(smoothScroller);
}
