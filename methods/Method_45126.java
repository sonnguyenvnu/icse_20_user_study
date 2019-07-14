private HttpResponse setupNormalResponse(final org.apache.http.HttpResponse remoteResponse) throws IOException {
  HttpVersion httpVersion=HttpVersion.valueOf(remoteResponse.getProtocolVersion().toString());
  HttpResponseStatus status=HttpResponseStatus.valueOf(remoteResponse.getStatusLine().getStatusCode());
  FullHttpResponse response=new DefaultFullHttpResponse(httpVersion,status);
  response.setStatus(status);
  Header[] allHeaders=remoteResponse.getAllHeaders();
  for (  Header header : allHeaders) {
    if (isResponseHeader(header)) {
      response.headers().set(header.getName(),header.getValue());
    }
  }
  HttpEntity entity=remoteResponse.getEntity();
  if (entity != null) {
    byte[] content=toByteArray(entity);
    if (content.length > 0) {
      ByteBuf buffer=Unpooled.copiedBuffer(content);
      response.content().writeBytes(buffer);
    }
  }
  return newResponse(response);
}
