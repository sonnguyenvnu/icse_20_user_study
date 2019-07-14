/** 
 * Configure the pipeline for a cleartext upgrade from HTTP to HTTP/2.
 */
private void configureClearTextWithHttpUpgrade(SocketChannel ch){
  HttpClientCodec sourceCodec=new HttpClientCodec();
  Http2ClientUpgradeCodec upgradeCodec=new Http2ClientUpgradeCodec(connectionHandler);
  HttpClientUpgradeHandler upgradeHandler=new HttpClientUpgradeHandler(sourceCodec,upgradeCodec,65536);
  ch.pipeline().addLast(sourceCodec,upgradeHandler,new UpgradeRequestHandler(),new UserEventLogger());
}
