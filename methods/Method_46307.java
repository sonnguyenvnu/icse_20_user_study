/** 
 * Configure the pipeline for a cleartext upgrade from HTTP to HTTP/2.0
 */
private void configureClearText(final SocketChannel ch){
  final ChannelPipeline p=ch.pipeline();
  final HttpServerCodec sourceCodec=new HttpServerCodec();
  final HttpServerUpgradeHandler upgradeHandler=new HttpServerUpgradeHandler(sourceCodec,new UpgradeCodecFactory(){
    @Override public HttpServerUpgradeHandler.UpgradeCodec newUpgradeCodec(    CharSequence protocol){
      if (AsciiString.contentEquals(Http2CodecUtil.HTTP_UPGRADE_PROTOCOL_NAME,protocol)) {
        return new Http2ServerUpgradeCodec(new Http2ChannelHandlerBuilder(serverHandler).build());
      }
 else {
        return null;
      }
    }
  }
);
  final Http2ServerUpgradeHandler cleartextHttp2ServerUpgradeHandler=new Http2ServerUpgradeHandler(bizGroup,sourceCodec,upgradeHandler,new Http2ChannelHandlerBuilder(serverHandler).build());
  p.addLast("Http2ServerUpgradeHandler",cleartextHttp2ServerUpgradeHandler);
  p.addLast("HttpDirectTalkingHandler",new SimpleChannelInboundHandler<HttpMessage>(){
    @Override protected void channelRead0(    ChannelHandlerContext ctx,    HttpMessage msg) throws Exception {
      if (LOGGER.isWarnEnabled()) {
        LOGGER.warn("Directly talking: {} (no upgrade was attempted) from {}",msg.protocolVersion(),NetUtil.toSocketAddressString(ch.remoteAddress()));
      }
      ChannelPipeline pipeline=ctx.pipeline();
      ChannelHandlerContext thisCtx=pipeline.context(this);
      pipeline.addAfter(bizGroup,thisCtx.name(),"Http1ChannelHandler",new Http1ServerChannelHandler(serverHandler));
      pipeline.replace(this,"HttpObjectAggregator",new HttpObjectAggregator(maxHttpContentLength));
      ctx.fireChannelRead(ReferenceCountUtil.retain(msg));
    }
  }
);
}
