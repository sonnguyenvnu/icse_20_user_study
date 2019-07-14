private Charset encodingFromContentTypeHeaderOrUtf8(){
  ContentTypeHeader contentTypeHeader=contentTypeHeader();
  if (contentTypeHeader != null) {
    return contentTypeHeader.charset();
  }
  return UTF_8;
}
