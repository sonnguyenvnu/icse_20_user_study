@Override public void didReceivedNotification(int id,int account,Object... args){
  if (id == NotificationCenter.didReplacedPhotoInMemCache) {
    String oldKey=(String)args[0];
    if (currentMediaKey != null && currentMediaKey.equals(oldKey)) {
      currentMediaKey=(String)args[1];
      currentMediaLocation=(ImageLocation)args[2];
      if (setImageBackup != null) {
        setImageBackup.mediaLocation=(ImageLocation)args[2];
      }
    }
    if (currentImageKey != null && currentImageKey.equals(oldKey)) {
      currentImageKey=(String)args[1];
      currentImageLocation=(ImageLocation)args[2];
      if (setImageBackup != null) {
        setImageBackup.imageLocation=(ImageLocation)args[2];
      }
    }
    if (currentThumbKey != null && currentThumbKey.equals(oldKey)) {
      currentThumbKey=(String)args[1];
      currentThumbLocation=(ImageLocation)args[2];
      if (setImageBackup != null) {
        setImageBackup.thumbLocation=(ImageLocation)args[2];
      }
    }
  }
}
