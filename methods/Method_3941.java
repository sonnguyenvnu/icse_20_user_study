/** 
 * Tells this RecyclerView to suppress all layout and scroll calls until layout suppression is disabled with a later call to suppressLayout(false). When layout suppression is disabled, a requestLayout() call is sent if requestLayout() was attempted while layout was being suppressed. <p> In addition to the layout suppression  {@link #smoothScrollBy(int,int)}, {@link #scrollBy(int,int)},  {@link #scrollToPosition(int)} and{@link #smoothScrollToPosition(int)} are dropped; TouchEvents and GenericMotionEvents aredropped;  {@link LayoutManager#onFocusSearchFailed(View,int,Recycler,State)} will not becalled. <p> <code>suppressLayout(true)</code> does not prevent app from directly calling  {@link LayoutManager#scrollToPosition(int)},  {@link LayoutManager#smoothScrollToPosition(RecyclerView,State,int)}. <p> {@link #setAdapter(Adapter)} and {@link #swapAdapter(Adapter,boolean)} will automaticallystop suppressing. <p> Note: Running ItemAnimator is not stopped automatically,  it's caller's responsibility to call ItemAnimator.end().
 * @param suppress true to suppress layout and scroll, false to re-enable.
 */
public final void suppressLayout(boolean suppress){
  if (suppress != mLayoutSuppressed) {
    assertNotInLayoutOrScroll("Do not suppressLayout in layout or scroll");
    if (!suppress) {
      mLayoutSuppressed=false;
      if (mLayoutWasDefered && mLayout != null && mAdapter != null) {
        requestLayout();
      }
      mLayoutWasDefered=false;
    }
 else {
      final long now=SystemClock.uptimeMillis();
      MotionEvent cancelEvent=MotionEvent.obtain(now,now,MotionEvent.ACTION_CANCEL,0.0f,0.0f,0);
      onTouchEvent(cancelEvent);
      mLayoutSuppressed=true;
      mIgnoreMotionEventTillDown=true;
      stopScroll();
    }
  }
}
