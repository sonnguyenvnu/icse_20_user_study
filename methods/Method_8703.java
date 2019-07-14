private void processBitmap(Bitmap bitmap){
  if (bitmap == null) {
    return;
  }
  bigPhoto=ImageLoader.scaleAndSaveImage(bitmap,800,800,80,false,320,320);
  smallPhoto=ImageLoader.scaleAndSaveImage(bitmap,150,150,80,false,150,150);
  if (smallPhoto != null) {
    try {
      Bitmap b=BitmapFactory.decodeFile(FileLoader.getPathToAttach(smallPhoto,true).getAbsolutePath());
      String key=smallPhoto.location.volume_id + "_" + smallPhoto.location.local_id + "@50_50";
      ImageLoader.getInstance().putImageToCache(new BitmapDrawable(b),key);
    }
 catch (    Throwable ignore) {
    }
  }
  bitmap.recycle();
  if (bigPhoto != null) {
    UserConfig.getInstance(currentAccount).saveConfig(false);
    uploadingImage=FileLoader.getDirectory(FileLoader.MEDIA_DIR_CACHE) + "/" + bigPhoto.location.volume_id + "_" + bigPhoto.location.local_id + ".jpg";
    if (uploadAfterSelect) {
      NotificationCenter.getInstance(currentAccount).addObserver(ImageUpdater.this,NotificationCenter.FileDidUpload);
      NotificationCenter.getInstance(currentAccount).addObserver(ImageUpdater.this,NotificationCenter.FileDidFailUpload);
      FileLoader.getInstance(currentAccount).uploadFile(uploadingImage,false,true,ConnectionsManager.FileTypePhoto);
    }
    if (delegate != null) {
      delegate.didUploadPhoto(null,bigPhoto,smallPhoto);
    }
  }
}
