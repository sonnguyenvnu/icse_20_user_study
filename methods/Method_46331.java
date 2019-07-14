private ChannelInitializer<SocketChannel> createChannelInitializer(){
  final RequestDispatcher dispatcher=createRequestDispatcher();
  if (sslContext == null) {
    return new ChannelInitializer<SocketChannel>(){
      @Override public void initChannel(      SocketChannel ch) throws Exception {
        setupHandlers(ch,dispatcher,HTTP);
      }
    }
;
  }
 else {
    final SSLEngine engine=sslContext.createSSLEngine();
    engine.setUseClientMode(false);
    return new ChannelInitializer<SocketChannel>(){
      @Override public void initChannel(      SocketChannel ch) throws Exception {
        ch.pipeline().addFirst(new SslHandler(engine));
        setupHandlers(ch,dispatcher,HTTPS);
      }
    }
;
  }
}
