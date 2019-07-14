private void onBitmapException(Drawable bitmapDrawable){
  if (bitmapDrawable == currentMediaDrawable && currentMediaKey != null) {
    ImageLoader.getInstance().removeImage(currentMediaKey);
    currentMediaKey=null;
  }
 else   if (bitmapDrawable == currentImageDrawable && currentImageKey != null) {
    ImageLoader.getInstance().removeImage(currentImageKey);
    currentImageKey=null;
  }
 else   if (bitmapDrawable == currentThumbDrawable && currentThumbKey != null) {
    ImageLoader.getInstance().removeImage(currentThumbKey);
    currentThumbKey=null;
  }
  setImage(currentMediaLocation,currentMediaFilter,currentImageLocation,currentImageFilter,currentThumbLocation,currentThumbFilter,currentThumbDrawable,currentSize,currentExt,currentParentObject,currentCacheType);
}
