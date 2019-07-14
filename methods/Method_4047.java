/** 
 * Checks for gaps if we've reached to the top of the list. <p> Intermediate gaps created by full span items are tracked via mLaidOutInvalidFullSpan field.
 */
View hasGapsToFix(){
  int startChildIndex=0;
  int endChildIndex=getChildCount() - 1;
  BitSet mSpansToCheck=new BitSet(mSpanCount);
  mSpansToCheck.set(0,mSpanCount,true);
  final int firstChildIndex, childLimit;
  final int preferredSpanDir=mOrientation == VERTICAL && isLayoutRTL() ? 1 : -1;
  if (mShouldReverseLayout) {
    firstChildIndex=endChildIndex;
    childLimit=startChildIndex - 1;
  }
 else {
    firstChildIndex=startChildIndex;
    childLimit=endChildIndex + 1;
  }
  final int nextChildDiff=firstChildIndex < childLimit ? 1 : -1;
  for (int i=firstChildIndex; i != childLimit; i+=nextChildDiff) {
    View child=getChildAt(i);
    LayoutParams lp=(LayoutParams)child.getLayoutParams();
    if (mSpansToCheck.get(lp.mSpan.mIndex)) {
      if (checkSpanForGap(lp.mSpan)) {
        return child;
      }
      mSpansToCheck.clear(lp.mSpan.mIndex);
    }
    if (lp.mFullSpan) {
      continue;
    }
    if (i + nextChildDiff != childLimit) {
      View nextChild=getChildAt(i + nextChildDiff);
      boolean compareSpans=false;
      if (mShouldReverseLayout) {
        int myEnd=mPrimaryOrientation.getDecoratedEnd(child);
        int nextEnd=mPrimaryOrientation.getDecoratedEnd(nextChild);
        if (myEnd < nextEnd) {
          return child;
        }
 else         if (myEnd == nextEnd) {
          compareSpans=true;
        }
      }
 else {
        int myStart=mPrimaryOrientation.getDecoratedStart(child);
        int nextStart=mPrimaryOrientation.getDecoratedStart(nextChild);
        if (myStart > nextStart) {
          return child;
        }
 else         if (myStart == nextStart) {
          compareSpans=true;
        }
      }
      if (compareSpans) {
        LayoutParams nextLp=(LayoutParams)nextChild.getLayoutParams();
        if (lp.mSpan.mIndex - nextLp.mSpan.mIndex < 0 != preferredSpanDir < 0) {
          return child;
        }
      }
    }
  }
  return null;
}
