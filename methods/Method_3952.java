/** 
 * Checks if the new focus candidate is a good enough candidate such that RecyclerView will assign it as the next focus View instead of letting view hierarchy decide. A good candidate means a View that is aligned in the focus direction wrt the focused View and is not the RecyclerView itself. When this method returns false, RecyclerView will let the parent make the decision so the same View may still get the focus as a result of that search.
 */
private boolean isPreferredNextFocus(View focused,View next,int direction){
  if (next == null || next == this) {
    return false;
  }
  if (findContainingItemView(next) == null) {
    return false;
  }
  if (focused == null) {
    return true;
  }
  if (findContainingItemView(focused) == null) {
    return true;
  }
  mTempRect.set(0,0,focused.getWidth(),focused.getHeight());
  mTempRect2.set(0,0,next.getWidth(),next.getHeight());
  offsetDescendantRectToMyCoords(focused,mTempRect);
  offsetDescendantRectToMyCoords(next,mTempRect2);
  final int rtl=mLayout.getLayoutDirection() == ViewCompat.LAYOUT_DIRECTION_RTL ? -1 : 1;
  int rightness=0;
  if ((mTempRect.left < mTempRect2.left || mTempRect.right <= mTempRect2.left) && mTempRect.right < mTempRect2.right) {
    rightness=1;
  }
 else   if ((mTempRect.right > mTempRect2.right || mTempRect.left >= mTempRect2.right) && mTempRect.left > mTempRect2.left) {
    rightness=-1;
  }
  int downness=0;
  if ((mTempRect.top < mTempRect2.top || mTempRect.bottom <= mTempRect2.top) && mTempRect.bottom < mTempRect2.bottom) {
    downness=1;
  }
 else   if ((mTempRect.bottom > mTempRect2.bottom || mTempRect.top >= mTempRect2.bottom) && mTempRect.top > mTempRect2.top) {
    downness=-1;
  }
switch (direction) {
case View.FOCUS_LEFT:
    return rightness < 0;
case View.FOCUS_RIGHT:
  return rightness > 0;
case View.FOCUS_UP:
return downness < 0;
case View.FOCUS_DOWN:
return downness > 0;
case View.FOCUS_FORWARD:
return downness > 0 || (downness == 0 && rightness * rtl >= 0);
case View.FOCUS_BACKWARD:
return downness < 0 || (downness == 0 && rightness * rtl <= 0);
}
throw new IllegalArgumentException("Invalid direction: " + direction + exceptionLabel());
}
