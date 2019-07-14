public boolean canStreamVideo(){
  TLRPC.Document document=getDocument();
  if (document == null || document instanceof TLRPC.TL_documentEncrypted) {
    return false;
  }
  if (SharedConfig.streamAllVideo) {
    return true;
  }
  for (int a=0; a < document.attributes.size(); a++) {
    TLRPC.DocumentAttribute attribute=document.attributes.get(a);
    if (attribute instanceof TLRPC.TL_documentAttributeVideo) {
      return attribute.supports_streaming;
    }
  }
  if (SharedConfig.streamMkv && "video/x-matroska".equals(document.mime_type)) {
    return true;
  }
  return false;
}
