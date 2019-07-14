protected void sendHttp1Response0(HttpResponseStatus status,boolean error,ByteBuf content){
  FullHttpResponse httpResponse=new DefaultFullHttpResponse(HTTP_1_1,status,content);
  HttpHeaders headers=httpResponse.headers();
  headers.setInt(CONTENT_LENGTH,httpResponse.content().readableBytes());
  if (request.getSerializeType() > 0) {
    String serialization=SerializerFactory.getAliasByCode(request.getSerializeType());
    headers.set(RemotingConstants.HEAD_SERIALIZE_TYPE,serialization);
  }
 else {
    headers.set(CONTENT_TYPE,"text/plain; charset=" + RpcConstants.DEFAULT_CHARSET.displayName());
  }
  if (error) {
    headers.set(RemotingConstants.HEAD_RESPONSE_ERROR,"true");
  }
  if (!keepAlive) {
    ctx.write(httpResponse).addListener(ChannelFutureListener.CLOSE);
  }
 else {
    httpResponse.headers().set(CONNECTION,HttpHeaderValues.KEEP_ALIVE);
    ctx.write(httpResponse);
  }
}
