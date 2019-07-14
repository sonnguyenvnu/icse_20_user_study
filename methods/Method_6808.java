public void checkMediaExistance(){
  File cacheFile=null;
  attachPathExists=false;
  mediaExists=false;
  if (type == 1) {
    TLRPC.PhotoSize currentPhotoObject=FileLoader.getClosestPhotoSizeWithSize(photoThumbs,AndroidUtilities.getPhotoSize());
    if (currentPhotoObject != null) {
      File file=FileLoader.getPathToMessage(messageOwner);
      if (needDrawBluredPreview()) {
        mediaExists=new File(file.getAbsolutePath() + ".enc").exists();
      }
      if (!mediaExists) {
        mediaExists=file.exists();
      }
    }
  }
 else   if (type == 8 || type == 3 || type == 9 || type == 2 || type == 14 || type == TYPE_ROUND_VIDEO) {
    if (messageOwner.attachPath != null && messageOwner.attachPath.length() > 0) {
      File f=new File(messageOwner.attachPath);
      attachPathExists=f.exists();
    }
    if (!attachPathExists) {
      File file=FileLoader.getPathToMessage(messageOwner);
      if (type == 3 && needDrawBluredPreview()) {
        mediaExists=new File(file.getAbsolutePath() + ".enc").exists();
      }
      if (!mediaExists) {
        mediaExists=file.exists();
      }
    }
  }
 else {
    TLRPC.Document document=getDocument();
    if (document != null) {
      if (isWallpaper()) {
        mediaExists=FileLoader.getPathToAttach(document,true).exists();
      }
 else {
        mediaExists=FileLoader.getPathToAttach(document).exists();
      }
    }
 else     if (type == 0) {
      TLRPC.PhotoSize currentPhotoObject=FileLoader.getClosestPhotoSizeWithSize(photoThumbs,AndroidUtilities.getPhotoSize());
      if (currentPhotoObject == null) {
        return;
      }
      if (currentPhotoObject != null) {
        mediaExists=FileLoader.getPathToAttach(currentPhotoObject,true).exists();
      }
    }
  }
}
