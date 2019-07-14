private void replyWithToken(final ChannelHandlerContext ctx,final Object msg,final String token){
  final String json="{\"token\": \"" + token + "\"}";
  final byte[] jsonBytes=json.getBytes();
  final FullHttpResponse response=new DefaultFullHttpResponse(HTTP_1_1,OK,Unpooled.wrappedBuffer(jsonBytes));
  response.headers().set(CONTENT_TYPE,"application/json");
  response.headers().set(CONTENT_LENGTH,response.content().readableBytes());
  ctx.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);
  ReferenceCountUtil.release(msg);
}
