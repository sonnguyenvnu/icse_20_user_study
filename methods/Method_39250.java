private static String contentTypeForFileName(final String contentType){
  return StringPool.DOT + contentType.substring(contentType.lastIndexOf("/") + 1,contentType.length());
}
