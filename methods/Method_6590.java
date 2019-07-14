private void recycleBitmap(String newKey,int type){
  String key;
  Drawable image;
  if (type == TYPE_MEDIA) {
    key=currentMediaKey;
    image=currentMediaDrawable;
  }
 else   if (type == TYPE_CROSSFDADE) {
    key=crossfadeKey;
    image=crossfadeImage;
  }
 else   if (type == TYPE_THUMB) {
    key=currentThumbKey;
    image=currentThumbDrawable;
  }
 else {
    key=currentImageKey;
    image=currentImageDrawable;
  }
  if (key != null && key.startsWith("-")) {
    String replacedKey=ImageLoader.getInstance().getReplacedKey(key);
    if (replacedKey != null) {
      key=replacedKey;
    }
  }
  String replacedKey=ImageLoader.getInstance().getReplacedKey(key);
  if (key != null && (newKey == null || !newKey.equals(key)) && image != null) {
    if (image instanceof AnimatedFileDrawable) {
      AnimatedFileDrawable fileDrawable=(AnimatedFileDrawable)image;
      fileDrawable.recycle();
    }
 else     if (image instanceof BitmapDrawable) {
      Bitmap bitmap=((BitmapDrawable)image).getBitmap();
      boolean canDelete=ImageLoader.getInstance().decrementUseCount(key);
      if (!ImageLoader.getInstance().isInCache(key)) {
        if (canDelete) {
          bitmap.recycle();
        }
      }
    }
  }
  if (type == TYPE_MEDIA) {
    currentMediaKey=null;
    currentMediaDrawable=null;
  }
 else   if (type == TYPE_CROSSFDADE) {
    crossfadeKey=null;
    crossfadeImage=null;
  }
 else   if (type == TYPE_THUMB) {
    currentThumbDrawable=null;
    currentThumbKey=null;
  }
 else {
    currentImageDrawable=null;
    currentImageKey=null;
  }
}
