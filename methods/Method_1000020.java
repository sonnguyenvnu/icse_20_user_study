@Override protected void decode(ChannelHandlerContext ctx,ByteBuf buffer,List<Object> out) throws Exception {
  byte[] encoded=new byte[buffer.readableBytes()];
  buffer.readBytes(encoded);
  P2pMessage msg=messageFactory.create(encoded);
  logger.info("Handshake Receive from {}, {}",ctx.channel().remoteAddress(),msg);
switch (msg.getType()) {
case P2P_HELLO:
    handleHelloMsg(ctx,(HelloMessage)msg);
  break;
case P2P_DISCONNECT:
if (channel.getNodeStatistics() != null) {
  channel.getNodeStatistics().nodeDisconnectedRemote(((DisconnectMessage)msg).getReasonCode());
}
channel.close();
break;
default :
channel.close();
break;
}
}
