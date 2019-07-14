private void sendError(final ChannelHandlerContext ctx,final Object msg){
  ctx.writeAndFlush(new DefaultFullHttpResponse(HTTP_1_1,UNAUTHORIZED)).addListener(ChannelFutureListener.CLOSE);
  ReferenceCountUtil.release(msg);
}
