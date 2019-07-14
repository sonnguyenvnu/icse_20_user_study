/** 
 * Scrolls the RV by 'dx' and 'dy' via calls to {@link LayoutManager#scrollHorizontallyBy(int,Recycler,State)} and{@link LayoutManager#scrollVerticallyBy(int,Recycler,State)}. Also sets how much of the scroll was actually consumed in 'consumed' parameter (indexes 0 and 1 for the x axis and y axis, respectively). This method should only be called in the context of an existing scroll operation such that any other necessary operations (such as a call to  {@link #consumePendingUpdateOperations()}) is already handled.
 */
void scrollStep(int dx,int dy,@Nullable int[] consumed){
  startInterceptRequestLayout();
  onEnterLayoutOrScroll();
  TraceCompat.beginSection(TRACE_SCROLL_TAG);
  fillRemainingScrollValues(mState);
  int consumedX=0;
  int consumedY=0;
  if (dx != 0) {
    consumedX=mLayout.scrollHorizontallyBy(dx,mRecycler,mState);
  }
  if (dy != 0) {
    consumedY=mLayout.scrollVerticallyBy(dy,mRecycler,mState);
  }
  TraceCompat.endSection();
  repositionShadowingViews();
  onExitLayoutOrScroll();
  stopInterceptRequestLayout(false);
  if (consumed != null) {
    consumed[0]=consumedX;
    consumed[1]=consumedY;
  }
}
