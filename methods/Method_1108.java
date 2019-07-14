@SuppressLint("Range") public void applyTo(Drawable drawable){
  if (drawable == null) {
    return;
  }
  if (mAlpha != UNSET) {
    drawable.setAlpha(mAlpha);
  }
  if (mIsSetColorFilter) {
    drawable.setColorFilter(mColorFilter);
  }
  if (mDither != UNSET) {
    drawable.setDither(mDither != 0);
  }
  if (mFilterBitmap != UNSET) {
    drawable.setFilterBitmap(mFilterBitmap != 0);
  }
}
