/** 
 * @return True if last span is the first one we want to fill
 */
private boolean preferLastSpan(int layoutDir){
  if (mOrientation == HORIZONTAL) {
    return (layoutDir == LayoutState.LAYOUT_START) != mShouldReverseLayout;
  }
  return ((layoutDir == LayoutState.LAYOUT_START) == mShouldReverseLayout) == isLayoutRTL();
}
