/** 
 * Determines bounds for the underlying drawable and a matrix that should be applied on it.
 */
private void configureBounds(){
  Drawable underlyingDrawable=getCurrent();
  Rect bounds=getBounds();
  int underlyingWidth=mUnderlyingWidth=underlyingDrawable.getIntrinsicWidth();
  int underlyingHeight=mUnderlyingHeight=underlyingDrawable.getIntrinsicHeight();
  if (underlyingWidth <= 0 || underlyingHeight <= 0) {
    underlyingDrawable.setBounds(bounds);
    mDrawMatrix=null;
  }
 else {
    underlyingDrawable.setBounds(0,0,underlyingWidth,underlyingHeight);
    mDrawMatrix=mMatrix;
  }
}
