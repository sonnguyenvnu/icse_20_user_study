@Override public void initChannel(SocketChannel ch) throws Exception {
  final Http2Connection connection=new DefaultHttp2Connection(false);
  connectionHandler=new HttpToHttp2ConnectionHandlerBuilder().frameListener(new DelegatingDecompressorFrameListener(connection,new InboundHttp2ToHttpAdapterBuilder(connection).maxContentLength(transportConfig.getPayload()).propagateSettings(true).build())).connection(connection).build();
  responseHandler=new Http2ClientChannelHandler();
  settingsHandler=new Http2SettingsHandler(ch.newPromise());
  String protocol=transportConfig.getProviderInfo().getProtocolType();
  if (RpcConstants.PROTOCOL_TYPE_H2.equals(protocol)) {
    configureSsl(ch);
  }
 else   if (RpcConstants.PROTOCOL_TYPE_H2C.equals(protocol)) {
    if (!useH2cPriorKnowledge) {
      configureClearTextWithHttpUpgrade(ch);
    }
 else {
      configureClearTextWithPriorKnowledge(ch);
    }
  }
}
