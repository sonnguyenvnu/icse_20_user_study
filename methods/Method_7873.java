private boolean isVideoBlock(TLRPC.PageBlock block){
  if (block instanceof TLRPC.TL_pageBlockVideo) {
    TLRPC.Document document=getDocumentWithId(((TLRPC.TL_pageBlockVideo)block).video_id);
    if (document != null) {
      return MessageObject.isVideoDocument(document);
    }
  }
  return false;
}
