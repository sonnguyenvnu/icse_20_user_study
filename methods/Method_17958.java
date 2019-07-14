private boolean animatingRootBoundsFromZero(Rect currentVisibleArea){
  return !mHasMounted && ((mRootHeightAnimation != null && currentVisibleArea.height() == 0) || (mRootWidthAnimation != null && currentVisibleArea.width() == 0));
}
