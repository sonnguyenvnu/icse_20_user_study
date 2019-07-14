private File getMediaFile(int index){
  if (imagesArr.isEmpty() || index >= imagesArr.size() || index < 0) {
    return null;
  }
  TLRPC.PageBlock block=imagesArr.get(index);
  if (block instanceof TLRPC.TL_pageBlockPhoto) {
    TLRPC.Photo photo=getPhotoWithId(((TLRPC.TL_pageBlockPhoto)block).photo_id);
    if (photo != null) {
      TLRPC.PhotoSize sizeFull=FileLoader.getClosestPhotoSizeWithSize(photo.sizes,AndroidUtilities.getPhotoSize());
      if (sizeFull != null) {
        return FileLoader.getPathToAttach(sizeFull,true);
      }
    }
  }
 else   if (block instanceof TLRPC.TL_pageBlockVideo) {
    TLRPC.Document document=getDocumentWithId(((TLRPC.TL_pageBlockVideo)block).video_id);
    if (document != null) {
      return FileLoader.getPathToAttach(document,true);
    }
  }
  return null;
}
