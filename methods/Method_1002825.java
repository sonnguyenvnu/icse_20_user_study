static HttpFile build(HttpFileBuilder builder,Clock clock,String pathOrUri,@Nullable String contentEncoding,HttpHeaders additionalHeaders){
  builder.autoDetectedContentType(false);
  builder.clock(clock);
  builder.setHeaders(additionalHeaders);
  final MediaType contentType=MimeTypeUtil.guessFromPath(pathOrUri,contentEncoding);
  if (contentType != null) {
    builder.contentType(contentType);
  }
  if (contentEncoding != null) {
    builder.setHeader(HttpHeaderNames.CONTENT_ENCODING,contentEncoding);
  }
  return builder.build();
}
