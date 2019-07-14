/** 
 * Calculates the views' layout order. (e.g. from end to start or start to end) RTL layout support is applied automatically. So if layout is RTL and {@link #getReverseLayout()} is {@code true}, elements will be laid out starting from left.
 */
private void resolveShouldLayoutReverse(){
  if (mOrientation == VERTICAL || !isLayoutRTL()) {
    mShouldReverseLayout=mReverseLayout;
  }
 else {
    mShouldReverseLayout=!mReverseLayout;
  }
}
