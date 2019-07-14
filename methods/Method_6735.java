public static int getWebDocumentDuration(TLRPC.WebDocument document){
  if (document == null) {
    return 0;
  }
  for (int a=0, size=document.attributes.size(); a < size; a++) {
    TLRPC.DocumentAttribute attribute=document.attributes.get(a);
    if (attribute instanceof TLRPC.TL_documentAttributeVideo) {
      return attribute.duration;
    }
 else     if (attribute instanceof TLRPC.TL_documentAttributeAudio) {
      return attribute.duration;
    }
  }
  return 0;
}
