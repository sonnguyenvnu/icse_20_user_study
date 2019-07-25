@Override public void disconnect(ChannelHandlerContext ctx,ChannelPromise promise) throws Exception {
  final String remoteAddress=RemoteHelper.parseChannelRemoteAddress(ctx.channel());
  LOGGER.debug("NETTY CLIENT PIPELINE: DISCONNECT {}",remoteAddress);
  closeChannel(ctx.channel());
  super.disconnect(ctx,promise);
}
