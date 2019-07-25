private static void forbidden(final ChannelHandlerContext ctx,final HttpRequest request){
  ctx.writeAndFlush(new DefaultFullHttpResponse(request.protocolVersion(),HttpResponseStatus.FORBIDDEN)).addListener(ChannelFutureListener.CLOSE);
}
