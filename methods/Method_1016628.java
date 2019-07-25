private RequestBody gzip(final RequestBody body){
  return new RequestBody(){
    @Override public MediaType contentType(){
      return body.contentType();
    }
    @Override public long contentLength(){
      return -1;
    }
    @Override public void writeTo(    final BufferedSink sink) throws IOException {
      BufferedSink gzipSink=Okio.buffer(new GzipSink(sink));
      body.writeTo(gzipSink);
      gzipSink.close();
    }
  }
;
}
