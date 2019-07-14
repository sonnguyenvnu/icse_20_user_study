public static RequestBody createInputStreamRequestBody(final MediaType mediaType,final InputStream inputStream){
  return new RequestBody(){
    @Override public MediaType contentType(){
      return mediaType;
    }
    @Override public long contentLength(){
      try {
        return inputStream.available();
      }
 catch (      IOException e) {
        return 0;
      }
    }
    @Override public void writeTo(    BufferedSink sink) throws IOException {
      Source source=null;
      try {
        source=Okio.source(inputStream);
        sink.writeAll(source);
      }
  finally {
        Util.closeQuietly(source);
      }
    }
  }
;
}
