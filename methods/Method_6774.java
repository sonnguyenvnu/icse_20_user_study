public static boolean isImageWebDocument(WebFile webDocument){
  return webDocument != null && !isGifDocument(webDocument) && webDocument.mime_type.startsWith("image/");
}
