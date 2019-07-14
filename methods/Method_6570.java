public BitmapHolder getThumbBitmapSafe(){
  Bitmap bitmap=null;
  String key=null;
  if (currentThumbDrawable instanceof BitmapDrawable) {
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
