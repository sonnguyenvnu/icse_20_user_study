@Override protected void channelRead0(final ChannelHandlerContext ctx,final ByteBuf msg) throws Exception {
  try {
    MessageContent content=content().withContent(new ByteBufInputStream(msg)).build();
    SocketRequest request=new DefaultSocketRequest(content);
    this.monitor.onMessageArrived(request);
    SocketResponse response=new DefaultSocketResponse();
    handleSession(new SessionContext(request,response));
    this.monitor.onMessageLeave(response);
    ctx.write(Unpooled.wrappedBuffer(response.getContent().getContent()));
  }
 catch (  Exception e) {
    this.monitor.onException(e);
  }
}
