public String getDocumentName(){
  TLRPC.Document document;
  if (messageOwner.media instanceof TLRPC.TL_messageMediaDocument) {
    return FileLoader.getDocumentFileName(messageOwner.media.document);
  }
 else   if (messageOwner.media instanceof TLRPC.TL_messageMediaWebPage) {
    return FileLoader.getDocumentFileName(messageOwner.media.webpage.document);
  }
  return "";
}
