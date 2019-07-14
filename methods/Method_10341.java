private byte[] createContentType(String type){
  String result=AsyncHttpClient.HEADER_CONTENT_TYPE + ": " + normalizeContentType(type) + STR_CR_LF;
  return result.getBytes();
}
