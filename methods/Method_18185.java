public void setWrappedDrawable(Drawable drawable){
  if (drawable instanceof ComparableDrawable) {
    throw new IllegalArgumentException("drawable is already a ComparableDrawable");
  }
  if (mDrawable != null) {
    mDrawable.setCallback(null);
  }
  mDrawable=drawable;
  if (drawable != null) {
    drawable.setCallback(this);
  }
}
