public int getBitmapWidth(){
  Drawable drawable=getDrawable();
  if (drawable instanceof LottieDrawable) {
    return drawable.getIntrinsicWidth();
  }
  AnimatedFileDrawable animation=getAnimation();
  if (animation != null) {
    return imageOrientation % 360 == 0 || imageOrientation % 360 == 180 ? animation.getIntrinsicWidth() : animation.getIntrinsicHeight();
  }
  Bitmap bitmap=getBitmap();
  if (bitmap == null) {
    if (staticThumbDrawable != null) {
      return staticThumbDrawable.getIntrinsicWidth();
    }
    return 1;
  }
  return imageOrientation % 360 == 0 || imageOrientation % 360 == 180 ? bitmap.getWidth() : bitmap.getHeight();
}
