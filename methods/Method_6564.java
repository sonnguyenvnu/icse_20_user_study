public boolean onAttachedToWindow(){
  NotificationCenter.getGlobalInstance().addObserver(this,NotificationCenter.didReplacedPhotoInMemCache);
  if (setImageBackup != null && (setImageBackup.imageLocation != null || setImageBackup.thumbLocation != null || setImageBackup.mediaLocation != null || setImageBackup.thumb != null)) {
    setImage(setImageBackup.mediaLocation,setImageBackup.mediaFilter,setImageBackup.imageLocation,setImageBackup.imageFilter,setImageBackup.thumbLocation,setImageBackup.thumbFilter,setImageBackup.thumb,setImageBackup.size,setImageBackup.ext,setImageBackup.parentObject,setImageBackup.cacheType);
    return true;
  }
  return false;
}
