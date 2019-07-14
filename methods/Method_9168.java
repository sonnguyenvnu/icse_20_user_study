private void didSelectPhotos(ArrayList<SendMessagesHelper.SendingMediaInfo> photos){
  try {
    if (!photos.isEmpty()) {
      SendMessagesHelper.SendingMediaInfo info=photos.get(0);
      if (info.path != null) {
        currentWallpaperPath=new File(FileLoader.getDirectory(FileLoader.MEDIA_DIR_CACHE),Utilities.random.nextInt() + ".jpg");
        Point screenSize=AndroidUtilities.getRealScreenSize();
        Bitmap bitmap=ImageLoader.loadBitmap(info.path,null,screenSize.x,screenSize.y,true);
        FileOutputStream stream=new FileOutputStream(currentWallpaperPath);
        bitmap.compress(Bitmap.CompressFormat.JPEG,87,stream);
        delegate.didSelectWallpaper(currentWallpaperPath,bitmap,true);
      }
    }
  }
 catch (  Throwable e) {
    FileLog.e(e);
  }
}
