/** 
 * Used for debugging. Validates that child views are laid out in correct order. This is important because rest of the algorithm relies on this constraint. In default layout, child 0 should be closest to screen position 0 and last child should be closest to position WIDTH or HEIGHT. In reverse layout, last child should be closes to screen position 0 and first child should be closest to position WIDTH  or HEIGHT
 */
void validateChildOrder(){
  Log.d(TAG,"validating child count " + getChildCount());
  if (getChildCount() < 1) {
    return;
  }
  int lastPos=getPosition(getChildAt(0));
  int lastScreenLoc=mOrientationHelper.getDecoratedStart(getChildAt(0));
  if (mShouldReverseLayout) {
    for (int i=1; i < getChildCount(); i++) {
      View child=getChildAt(i);
      int pos=getPosition(child);
      int screenLoc=mOrientationHelper.getDecoratedStart(child);
      if (pos < lastPos) {
        logChildren();
        throw new RuntimeException("detected invalid position. loc invalid? " + (screenLoc < lastScreenLoc));
      }
      if (screenLoc > lastScreenLoc) {
        logChildren();
        throw new RuntimeException("detected invalid location");
      }
    }
  }
 else {
    for (int i=1; i < getChildCount(); i++) {
      View child=getChildAt(i);
      int pos=getPosition(child);
      int screenLoc=mOrientationHelper.getDecoratedStart(child);
      if (pos < lastPos) {
        logChildren();
        throw new RuntimeException("detected invalid position. loc invalid? " + (screenLoc < lastScreenLoc));
      }
      if (screenLoc < lastScreenLoc) {
        logChildren();
        throw new RuntimeException("detected invalid location");
      }
    }
  }
}
