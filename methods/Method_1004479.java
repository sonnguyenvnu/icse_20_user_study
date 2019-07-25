@Override public void process(SyncRequestEntry requestEntry){
  final ChannelHandlerContext ctx=requestEntry.getCtx();
  final long messageLogMaxOffset=storage.getMaxMessageOffset();
  final long actionLogMaxOffset=storage.getMaxActionLogOffset();
  slaveMessageLogLag=messageLogMaxOffset - requestEntry.getSyncRequest().getMessageLogOffset();
  slaveActionLogLag=actionLogMaxOffset - requestEntry.getSyncRequest().getActionLogOffset();
  final HeartbeatPayloadHolder payloadHolder=new HeartbeatPayloadHolder(messageLogMaxOffset,actionLogMaxOffset);
  final Datagram datagram=RemotingBuilder.buildResponseDatagram(CommandCode.SUCCESS,requestEntry.getRequestHeader(),payloadHolder);
  ctx.writeAndFlush(datagram);
}
