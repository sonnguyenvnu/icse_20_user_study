private TLRPC.PhotoSize getFileLocation(TLObject media,int[] size){
  if (media instanceof TLRPC.Photo) {
    TLRPC.Photo photo=(TLRPC.Photo)media;
    TLRPC.PhotoSize sizeFull=FileLoader.getClosestPhotoSizeWithSize(photo.sizes,AndroidUtilities.getPhotoSize());
    if (sizeFull != null) {
      size[0]=sizeFull.size;
      if (size[0] == 0) {
        size[0]=-1;
      }
      return sizeFull;
    }
 else {
      size[0]=-1;
    }
  }
 else   if (media instanceof TLRPC.Document) {
    TLRPC.Document document=(TLRPC.Document)media;
    TLRPC.PhotoSize thumb=FileLoader.getClosestPhotoSizeWithSize(document.thumbs,90);
    if (thumb != null) {
      size[0]=thumb.size;
      if (size[0] == 0) {
        size[0]=-1;
      }
      return thumb;
    }
  }
  return null;
}
