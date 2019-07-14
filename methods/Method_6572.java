public int getBitmapHeight(){
  Drawable drawable=getDrawable();
  if (drawable instanceof LottieDrawable) {
    return drawable.getIntrinsicHeight();
  }
  AnimatedFileDrawable animation=getAnimation();
  if (animation != null) {
    return imageOrientation % 360 == 0 || imageOrientation % 360 == 180 ? animation.getIntrinsicHeight() : animation.getIntrinsicWidth();
  }
  Bitmap bitmap=getBitmap();
  if (bitmap == null) {
    if (staticThumbDrawable != null) {
      return staticThumbDrawable.getIntrinsicHeight();
    }
    return 1;
  }
  return imageOrientation % 360 == 0 || imageOrientation % 360 == 180 ? bitmap.getHeight() : bitmap.getWidth();
}
