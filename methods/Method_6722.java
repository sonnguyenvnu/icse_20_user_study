public static boolean isRoundVideoDocument(TLRPC.Document document){
  if (document != null && "video/mp4".equals(document.mime_type)) {
    int width=0;
    int height=0;
    boolean round=false;
    for (int a=0; a < document.attributes.size(); a++) {
      TLRPC.DocumentAttribute attribute=document.attributes.get(a);
      if (attribute instanceof TLRPC.TL_documentAttributeVideo) {
        width=attribute.w;
        height=attribute.w;
        round=attribute.round_message;
      }
    }
    if (round && width <= 1280 && height <= 1280) {
      return true;
    }
  }
  return false;
}
