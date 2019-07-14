public static boolean isVideoWebDocument(WebFile webDocument){
  return webDocument != null && webDocument.mime_type.startsWith("video/");
}
