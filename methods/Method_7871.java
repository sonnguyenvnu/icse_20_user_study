private TLObject getMedia(int index){
  if (imagesArr.isEmpty() || index >= imagesArr.size() || index < 0) {
    return null;
  }
  TLRPC.PageBlock block=imagesArr.get(index);
  if (block instanceof TLRPC.TL_pageBlockPhoto) {
    return getPhotoWithId(((TLRPC.TL_pageBlockPhoto)block).photo_id);
  }
 else   if (block instanceof TLRPC.TL_pageBlockVideo) {
    return getDocumentWithId(((TLRPC.TL_pageBlockVideo)block).video_id);
  }
  return null;
}
