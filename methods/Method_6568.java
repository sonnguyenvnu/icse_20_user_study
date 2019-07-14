public Bitmap getBitmap(){
  AnimatedFileDrawable animation=getAnimation();
  if (animation != null && animation.hasBitmap()) {
    return animation.getAnimatedBitmap();
  }
 else   if (currentMediaDrawable instanceof BitmapDrawable && !(currentMediaDrawable instanceof AnimatedFileDrawable)) {
    return ((BitmapDrawable)currentMediaDrawable).getBitmap();
  }
 else   if (currentImageDrawable instanceof BitmapDrawable && !(currentImageDrawable instanceof AnimatedFileDrawable)) {
    return ((BitmapDrawable)currentImageDrawable).getBitmap();
  }
 else   if (currentThumbDrawable instanceof BitmapDrawable && !(currentThumbDrawable instanceof AnimatedFileDrawable)) {
    return ((BitmapDrawable)currentThumbDrawable).getBitmap();
  }
 else   if (staticThumbDrawable instanceof BitmapDrawable) {
    return ((BitmapDrawable)staticThumbDrawable).getBitmap();
  }
  return null;
}
