public static void ensureMediaThumbExists(int currentAccount,boolean isEncrypted,TLObject object,String path,Uri uri,long startTime){
  if (object instanceof TLRPC.TL_photo) {
    TLRPC.TL_photo photo=(TLRPC.TL_photo)object;
    boolean smallExists;
    TLRPC.PhotoSize smallSize=FileLoader.getClosestPhotoSizeWithSize(photo.sizes,90);
    if (smallSize instanceof TLRPC.TL_photoStrippedSize) {
      smallExists=true;
    }
 else {
      File smallFile=FileLoader.getPathToAttach(smallSize,true);
      smallExists=smallFile.exists();
    }
    TLRPC.PhotoSize bigSize=FileLoader.getClosestPhotoSizeWithSize(photo.sizes,AndroidUtilities.getPhotoSize());
    File bigFile=FileLoader.getPathToAttach(bigSize,false);
    boolean bigExists=bigFile.exists();
    if (!smallExists || !bigExists) {
      Bitmap bitmap=ImageLoader.loadBitmap(path,uri,AndroidUtilities.getPhotoSize(),AndroidUtilities.getPhotoSize(),true);
      if (bitmap == null) {
        bitmap=ImageLoader.loadBitmap(path,uri,800,800,true);
      }
      if (!bigExists) {
        TLRPC.PhotoSize size=ImageLoader.scaleAndSaveImage(bigSize,bitmap,AndroidUtilities.getPhotoSize(),AndroidUtilities.getPhotoSize(),80,false,101,101);
        if (size != bigSize) {
          photo.sizes.add(0,size);
        }
      }
      if (!smallExists) {
        TLRPC.PhotoSize size=ImageLoader.scaleAndSaveImage(smallSize,bitmap,90,90,55,true);
        if (size != smallSize) {
          photo.sizes.add(0,size);
        }
      }
      if (bitmap != null) {
        bitmap.recycle();
      }
    }
  }
 else   if (object instanceof TLRPC.TL_document) {
    TLRPC.TL_document document=(TLRPC.TL_document)object;
    if ((MessageObject.isVideoDocument(document) || MessageObject.isNewGifDocument(document)) && MessageObject.isDocumentHasThumb(document)) {
      TLRPC.PhotoSize photoSize=FileLoader.getClosestPhotoSizeWithSize(document.thumbs,320);
      if (photoSize instanceof TLRPC.TL_photoStrippedSize) {
        return;
      }
      File smallFile=FileLoader.getPathToAttach(photoSize,true);
      if (!smallFile.exists()) {
        Bitmap thumb=createVideoThumbnail(path,startTime);
        if (thumb == null) {
          thumb=ThumbnailUtils.createVideoThumbnail(path,MediaStore.Video.Thumbnails.MINI_KIND);
        }
        int side=isEncrypted ? 90 : 320;
        document.thumbs.set(0,ImageLoader.scaleAndSaveImage(photoSize,thumb,side,side,side > 90 ? 80 : 55,false));
      }
    }
  }
}
