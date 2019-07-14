public void onDetachedFromWindow(){
  if (currentImageLocation != null || currentMediaLocation != null || currentThumbLocation != null || staticThumbDrawable != null) {
    if (setImageBackup == null) {
      setImageBackup=new SetImageBackup();
    }
    setImageBackup.mediaLocation=currentMediaLocation;
    setImageBackup.mediaFilter=currentMediaFilter;
    setImageBackup.imageLocation=currentImageLocation;
    setImageBackup.imageFilter=currentImageFilter;
    setImageBackup.thumbLocation=currentThumbLocation;
    setImageBackup.thumbFilter=currentThumbFilter;
    setImageBackup.thumb=staticThumbDrawable;
    setImageBackup.size=currentSize;
    setImageBackup.ext=currentExt;
    setImageBackup.cacheType=currentCacheType;
    setImageBackup.parentObject=currentParentObject;
  }
  NotificationCenter.getGlobalInstance().removeObserver(this,NotificationCenter.didReplacedPhotoInMemCache);
  clearImage();
}
