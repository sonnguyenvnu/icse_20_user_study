private static HttpEntity buildEntityFrom(Request originalRequest){
  ContentTypeHeader contentTypeHeader=originalRequest.contentTypeHeader().or("text/plain");
  ContentType contentType=ContentType.create(contentTypeHeader.mimeTypePart(),contentTypeHeader.encodingPart().or("utf-8"));
  if (originalRequest.containsHeader(TRANSFER_ENCODING) && originalRequest.header(TRANSFER_ENCODING).firstValue().equals("chunked")) {
    return applyGzipWrapperIfRequired(originalRequest,new InputStreamEntity(new ByteArrayInputStream(originalRequest.getBody()),-1,contentType));
  }
  return applyGzipWrapperIfRequired(originalRequest,new ByteArrayEntity(originalRequest.getBody()));
}
