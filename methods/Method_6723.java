public static boolean isNewGifDocument(WebFile document){
  if (document != null && "video/mp4".equals(document.mime_type)) {
    int width=0;
    int height=0;
    boolean animated=false;
    for (int a=0; a < document.attributes.size(); a++) {
      TLRPC.DocumentAttribute attribute=document.attributes.get(a);
      if (attribute instanceof TLRPC.TL_documentAttributeAnimated) {
        animated=true;
      }
 else       if (attribute instanceof TLRPC.TL_documentAttributeVideo) {
        width=attribute.w;
        height=attribute.w;
      }
    }
    if (width <= 1280 && height <= 1280) {
      return true;
    }
  }
  return false;
}
