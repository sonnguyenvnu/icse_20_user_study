private static MessageContent toMessageContent(final FullHttpRequest request){
  long contentLength=HttpUtil.getContentLength(request,-1);
  if (contentLength <= 0) {
    return content().build();
  }
  return content().withCharset(HttpUtil.getCharset(request)).withContent(new ByteBufInputStream(request.content())).build();
}
