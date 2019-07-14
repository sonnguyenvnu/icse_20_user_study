public void addPart(String key,String value,String contentType){
  try {
    out.write(boundaryLine);
    out.write(createContentDisposition(key));
    out.write(createContentType(contentType));
    out.write(CR_LF);
    out.write(value.getBytes());
    out.write(CR_LF);
  }
 catch (  final IOException e) {
    AsyncHttpClient.log.e(LOG_TAG,"addPart ByteArrayOutputStream exception",e);
  }
}
