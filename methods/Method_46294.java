protected int sendHttp1Response(ChannelHandlerContext ctx,HttpResponseStatus status,String resultStr,boolean isKeepAlive){
  ByteBuf content=Unpooled.copiedBuffer(resultStr,RpcConstants.DEFAULT_CHARSET);
  FullHttpResponse res=new DefaultFullHttpResponse(HTTP_1_1,status,content);
  res.headers().set(CONTENT_TYPE,"text/html; charset=" + RpcConstants.DEFAULT_CHARSET.displayName());
  HttpUtil.setContentLength(res,content.readableBytes());
  try {
    ChannelFuture f=ctx.channel().writeAndFlush(res);
    if (isKeepAlive) {
      HttpUtil.setKeepAlive(res,true);
    }
 else {
      HttpUtil.setKeepAlive(res,false);
      f.addListener(ChannelFutureListener.CLOSE);
    }
  }
 catch (  Exception e2) {
    LOGGER.warn("Failed to send HTTP response to remote, cause by:",e2);
  }
  return content.readableBytes();
}
