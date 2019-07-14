/** 
 * Configure the pipeline for TLS NPN negotiation to HTTP/2.
 */
private void configureSSL(SocketChannel ch){
  final ChannelPipeline p=ch.pipeline();
  p.addLast(sslCtx.newHandler(ch.alloc()));
  p.addLast(new ApplicationProtocolNegotiationHandler(ApplicationProtocolNames.HTTP_1_1){
    @Override protected void configurePipeline(    ChannelHandlerContext ctx,    String protocol) throws Exception {
      if (ApplicationProtocolNames.HTTP_2.equals(protocol)) {
        ctx.pipeline().addLast(bizGroup,"Http2ChannelHandler",new Http2ChannelHandlerBuilder(serverHandler).build());
        return;
      }
      if (ApplicationProtocolNames.HTTP_1_1.equals(protocol)) {
        ctx.pipeline().addLast("HttpServerCodec",new HttpServerCodec());
        ctx.pipeline().addLast("HttpObjectAggregator",new HttpObjectAggregator(maxHttpContentLength));
        ctx.pipeline().addLast(bizGroup,"Http1ChannelHandler",new Http1ServerChannelHandler(serverHandler));
        return;
      }
      throw new IllegalStateException("unknown protocol: " + protocol);
    }
  }
);
}
