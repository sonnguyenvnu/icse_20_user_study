@Override protected void channelRead0(ChannelHandlerContext ctx,NettyRpcCmd cmd){
  String key=cmd.getKey();
  log.debug("cmd->{}",cmd);
  if (cmd.getMsg() != null && MessageConstants.ACTION_HEART_CHECK.equals(cmd.getMsg().getAction())) {
    if (NettyContext.currentType().equals(NettyType.client)) {
      heartbeatListener.onTcReceivedHeart(cmd);
      ctx.writeAndFlush(cmd);
      return;
    }
 else {
      heartbeatListener.onTmReceivedHeart(cmd);
      return;
    }
  }
  if (!StringUtils.isEmpty(key)) {
    RpcContent rpcContent=cmd.loadRpcContent();
    if (rpcContent != null) {
      log.debug("got response message[Netty Handler]");
      rpcContent.setRes(cmd.getMsg());
      rpcContent.signal();
    }
 else {
      ctx.fireChannelRead(cmd);
    }
  }
 else {
    ctx.fireChannelRead(cmd);
  }
}
