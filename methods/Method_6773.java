public static boolean isAnimatedStickerDocument(TLRPC.Document document){
  return SharedConfig.showAnimatedStickers && document != null && "application/x-tgsticker".equals(document.mime_type) && !document.thumbs.isEmpty();
}
