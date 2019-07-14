protected void sendHttp2Response(ChannelHandlerContext ctx,int streamId,HttpResponseStatus status,String result){
  Http2Headers headers=new DefaultHttp2Headers().status(status.codeAsText());
  if (!HttpResponseStatus.OK.equals(status)) {
    headers.set(RemotingConstants.HEAD_RESPONSE_ERROR,"true");
  }
  if (StringUtils.isNotBlank(result)) {
    ByteBuf data=ctx.alloc().buffer();
    data.writeBytes(result.getBytes(RpcConstants.DEFAULT_CHARSET));
    encoder().writeHeaders(ctx,streamId,headers,0,false,ctx.newPromise());
    encoder().writeData(ctx,streamId,data,0,true,ctx.newPromise());
  }
 else {
    encoder().writeHeaders(ctx,streamId,headers,0,true,ctx.newPromise());
  }
}
