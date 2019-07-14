public BitmapHolder getBitmapSafe(){
  Bitmap bitmap=null;
  String key=null;
  AnimatedFileDrawable animation=getAnimation();
  if (animation != null && animation.hasBitmap()) {
    bitmap=animation.getAnimatedBitmap();
  }
 else   if (currentMediaDrawable instanceof BitmapDrawable && !(currentMediaDrawable instanceof AnimatedFileDrawable)) {
    bitmap=((BitmapDrawable)currentMediaDrawable).getBitmap();
    key=currentMediaKey;
  }
 else   if (currentImageDrawable instanceof BitmapDrawable && !(currentImageDrawable instanceof AnimatedFileDrawable)) {
    bitmap=((BitmapDrawable)currentImageDrawable).getBitmap();
    key=currentImageKey;
  }
 else   if (currentThumbDrawable instanceof BitmapDrawable && !(currentThumbDrawable instanceof AnimatedFileDrawable)) {
    bitmap=((BitmapDrawable)currentThumbDrawable).getBitmap();
    key=currentThumbKey;
  }
 else   if (staticThumbDrawable instanceof BitmapDrawable) {
    bitmap=((BitmapDrawable)staticThumbDrawable).getBitmap();
  }
  if (bitmap != null) {
    return new BitmapHolder(bitmap,key);
  }
  return null;
}
