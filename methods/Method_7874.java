private String getMediaMime(int index){
  if (index >= imagesArr.size() || index < 0) {
    return "image/jpeg";
  }
  TLRPC.PageBlock block=imagesArr.get(index);
  if (block instanceof TLRPC.TL_pageBlockVideo) {
    TLRPC.TL_pageBlockVideo pageBlockVideo=(TLRPC.TL_pageBlockVideo)block;
    TLRPC.Document document=getDocumentWithId(pageBlockVideo.video_id);
    if (document != null) {
      return document.mime_type;
    }
  }
  return "image/jpeg";
}
