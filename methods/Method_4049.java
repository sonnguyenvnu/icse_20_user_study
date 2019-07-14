/** 
 * Sets the number of spans for the layout. This will invalidate all of the span assignments for Views. <p> Calling this method will automatically result in a new layout request unless the spanCount parameter is equal to current span count.
 * @param spanCount Number of spans to layout
 */
public void setSpanCount(int spanCount){
  assertNotInLayoutOrScroll(null);
  if (spanCount != mSpanCount) {
    invalidateSpanAssignments();
    mSpanCount=spanCount;
    mRemainingSpans=new BitSet(mSpanCount);
    mSpans=new Span[mSpanCount];
    for (int i=0; i < mSpanCount; i++) {
      mSpans[i]=new Span(i);
    }
    requestLayout();
  }
}
