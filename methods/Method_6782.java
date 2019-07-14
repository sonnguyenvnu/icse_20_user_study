public String getStrickerChar(){
  if (messageOwner.media != null && messageOwner.media.document != null) {
    for (    TLRPC.DocumentAttribute attribute : messageOwner.media.document.attributes) {
      if (attribute instanceof TLRPC.TL_documentAttributeSticker) {
        return attribute.alt;
      }
    }
  }
  return null;
}
