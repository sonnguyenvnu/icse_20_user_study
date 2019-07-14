private static HttpEntity applyGzipWrapperIfRequired(Request originalRequest,HttpEntity content){
  if (originalRequest.containsHeader(CONTENT_ENCODING) && originalRequest.header(CONTENT_ENCODING).firstValue().contains("gzip")) {
    return new GzipCompressingEntity(content);
  }
  return content;
}
