public static boolean isVideoDocument(TLRPC.Document document){
  if (document != null) {
    boolean isAnimated=false;
    boolean isVideo=false;
    int width=0;
    int height=0;
    for (int a=0; a < document.attributes.size(); a++) {
      TLRPC.DocumentAttribute attribute=document.attributes.get(a);
      if (attribute instanceof TLRPC.TL_documentAttributeVideo) {
        if (attribute.round_message) {
          return false;
        }
        isVideo=true;
        width=attribute.w;
        height=attribute.h;
      }
 else       if (attribute instanceof TLRPC.TL_documentAttributeAnimated) {
        isAnimated=true;
      }
    }
    if (isAnimated && (width > 1280 || height > 1280)) {
      isAnimated=false;
    }
    if (SharedConfig.streamMkv && !isVideo && "video/x-matroska".equals(document.mime_type)) {
      isVideo=true;
    }
    return isVideo && !isAnimated;
  }
  return false;
}
