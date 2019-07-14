public TLRPC.Document getStreamingMedia(){
  return documentAttachType == DOCUMENT_ATTACH_TYPE_VIDEO || documentAttachType == DOCUMENT_ATTACH_TYPE_ROUND || documentAttachType == DOCUMENT_ATTACH_TYPE_GIF ? documentAttach : null;
}
