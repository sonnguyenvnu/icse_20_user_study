private void didSelectPhotos(ArrayList<SendMessagesHelper.SendingMediaInfo> photos){
  if (!photos.isEmpty()) {
    SendMessagesHelper.SendingMediaInfo info=photos.get(0);
    Bitmap bitmap=null;
    if (info.path != null) {
      bitmap=ImageLoader.loadBitmap(info.path,null,800,800,true);
    }
 else     if (info.searchImage != null) {
      if (info.searchImage.photo != null) {
        TLRPC.PhotoSize photoSize=FileLoader.getClosestPhotoSizeWithSize(info.searchImage.photo.sizes,AndroidUtilities.getPhotoSize());
        if (photoSize != null) {
          File path=FileLoader.getPathToAttach(photoSize,true);
          finalPath=path.getAbsolutePath();
          if (!path.exists()) {
            path=FileLoader.getPathToAttach(photoSize,false);
            if (!path.exists()) {
              path=null;
            }
          }
          if (path != null) {
            bitmap=ImageLoader.loadBitmap(path.getAbsolutePath(),null,800,800,true);
          }
 else {
            NotificationCenter.getInstance(currentAccount).addObserver(ImageUpdater.this,NotificationCenter.fileDidLoad);
            NotificationCenter.getInstance(currentAccount).addObserver(ImageUpdater.this,NotificationCenter.fileDidFailedLoad);
            uploadingImage=FileLoader.getAttachFileName(photoSize.location);
            imageReceiver.setImage(ImageLocation.getForPhoto(photoSize,info.searchImage.photo),null,null,"jpg",null,1);
          }
        }
      }
 else       if (info.searchImage.imageUrl != null) {
        String md5=Utilities.MD5(info.searchImage.imageUrl) + "." + ImageLoader.getHttpUrlExtension(info.searchImage.imageUrl,"jpg");
        File cacheFile=new File(FileLoader.getDirectory(FileLoader.MEDIA_DIR_CACHE),md5);
        finalPath=cacheFile.getAbsolutePath();
        if (cacheFile.exists() && cacheFile.length() != 0) {
          bitmap=ImageLoader.loadBitmap(cacheFile.getAbsolutePath(),null,800,800,true);
        }
 else {
          uploadingImage=info.searchImage.imageUrl;
          NotificationCenter.getInstance(currentAccount).addObserver(ImageUpdater.this,NotificationCenter.httpFileDidLoad);
          NotificationCenter.getInstance(currentAccount).addObserver(ImageUpdater.this,NotificationCenter.httpFileDidFailedLoad);
          imageReceiver.setImage(info.searchImage.imageUrl,null,null,"jpg",1);
        }
      }
 else {
        bitmap=null;
      }
    }
    processBitmap(bitmap);
  }
}
