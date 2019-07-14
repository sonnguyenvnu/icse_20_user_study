/** 
 * Determines bounds for the underlying drawable and a matrix that should be applied on it. Adopted from android.widget.ImageView
 */
@VisibleForTesting void configureBounds(){
  Drawable underlyingDrawable=getCurrent();
  Rect bounds=getBounds();
  int viewWidth=bounds.width();
  int viewHeight=bounds.height();
  int underlyingWidth=mUnderlyingWidth=underlyingDrawable.getIntrinsicWidth();
  int underlyingHeight=mUnderlyingHeight=underlyingDrawable.getIntrinsicHeight();
  if (underlyingWidth <= 0 || underlyingHeight <= 0) {
    underlyingDrawable.setBounds(bounds);
    mDrawMatrix=null;
    return;
  }
  if (underlyingWidth == viewWidth && underlyingHeight == viewHeight) {
    underlyingDrawable.setBounds(bounds);
    mDrawMatrix=null;
    return;
  }
  if (mScaleType == ScaleType.FIT_XY) {
    underlyingDrawable.setBounds(bounds);
    mDrawMatrix=null;
    return;
  }
  underlyingDrawable.setBounds(0,0,underlyingWidth,underlyingHeight);
  mScaleType.getTransform(mTempMatrix,bounds,underlyingWidth,underlyingHeight,(mFocusPoint != null) ? mFocusPoint.x : 0.5f,(mFocusPoint != null) ? mFocusPoint.y : 0.5f);
  mDrawMatrix=mTempMatrix;
}
