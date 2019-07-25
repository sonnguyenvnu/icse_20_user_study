public void init(ChannelPipeline pipeline,String remoteId,boolean discoveryMode,ChannelManager channelManager){
  this.channelManager=channelManager;
  isActive=remoteId != null && !remoteId.isEmpty();
  startTime=System.currentTimeMillis();
  pipeline.addLast("readTimeoutHandler",new ReadTimeoutHandler(60,TimeUnit.SECONDS));
  pipeline.addLast(stats.tcp);
  pipeline.addLast("protoPender",new ProtobufVarint32LengthFieldPrepender());
  pipeline.addLast("lengthDecode",new TrxProtobufVarint32FrameDecoder(this));
  pipeline.addLast("handshakeHandler",handshakeHandler);
  messageCodec.setChannel(this);
  msgQueue.setChannel(this);
  handshakeHandler.setChannel(this,remoteId);
  p2pHandler.setChannel(this);
  tronNetHandler.setChannel(this);
  p2pHandler.setMsgQueue(msgQueue);
  tronNetHandler.setMsgQueue(msgQueue);
}
