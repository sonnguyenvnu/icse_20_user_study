/** 
 * Checks for gaps in the UI that may be caused by adapter changes. <p> When a full span item is laid out in reverse direction, it sets a flag which we check when scroll is stopped (or re-layout happens) and re-layout after first valid item.
 */
boolean checkForGaps(){
  if (getChildCount() == 0 || mGapStrategy == GAP_HANDLING_NONE || !isAttachedToWindow()) {
    return false;
  }
  final int minPos, maxPos;
  if (mShouldReverseLayout) {
    minPos=getLastChildPosition();
    maxPos=getFirstChildPosition();
  }
 else {
    minPos=getFirstChildPosition();
    maxPos=getLastChildPosition();
  }
  if (minPos == 0) {
    View gapView=hasGapsToFix();
    if (gapView != null) {
      mLazySpanLookup.clear();
      requestSimpleAnimationsInNextLayout();
      requestLayout();
      return true;
    }
  }
  if (!mLaidOutInvalidFullSpan) {
    return false;
  }
  int invalidGapDir=mShouldReverseLayout ? LayoutState.LAYOUT_START : LayoutState.LAYOUT_END;
  final LazySpanLookup.FullSpanItem invalidFsi=mLazySpanLookup.getFirstFullSpanItemInRange(minPos,maxPos + 1,invalidGapDir,true);
  if (invalidFsi == null) {
    mLaidOutInvalidFullSpan=false;
    mLazySpanLookup.forceInvalidateAfter(maxPos + 1);
    return false;
  }
  final LazySpanLookup.FullSpanItem validFsi=mLazySpanLookup.getFirstFullSpanItemInRange(minPos,invalidFsi.mPosition,invalidGapDir * -1,true);
  if (validFsi == null) {
    mLazySpanLookup.forceInvalidateAfter(invalidFsi.mPosition);
  }
 else {
    mLazySpanLookup.forceInvalidateAfter(validFsi.mPosition + 1);
  }
  requestSimpleAnimationsInNextLayout();
  requestLayout();
  return true;
}
