public void mount(T drawable,DrawableMatrix matrix){
  if (mDrawable == drawable) {
    return;
  }
  if (mDrawable != null) {
    setDrawableVisibilitySafe(false,false);
    mDrawable.setCallback(null);
  }
  mDrawable=drawable;
  if (mDrawable != null) {
    setDrawableVisibilitySafe(isVisible(),false);
    mDrawable.setCallback(this);
  }
  mMatrix=matrix;
  mShouldClipRect=(mMatrix != null && mMatrix.shouldClipRect()) || (Build.VERSION.SDK_INT < HONEYCOMB && mDrawable instanceof ColorDrawable) || (mDrawable instanceof InsetDrawable);
  invalidateSelf();
}
