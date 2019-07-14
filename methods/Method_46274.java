private void sendHttp2Response0(HttpResponseStatus status,boolean error,ByteBuf data){
  Http2Headers headers=new DefaultHttp2Headers().status(status.codeAsText());
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
  if (data != null) {
    encoder.writeHeaders(ctx,streamId,headers,0,false,ctx.newPromise());
    encoder.writeData(ctx,streamId,data,0,true,ctx.newPromise());
  }
 else {
    encoder.writeHeaders(ctx,streamId,headers,0,true,ctx.newPromise());
  }
}
