@Override public void userEventTriggered(ChannelHandlerContext ctx,Object evt) throws Exception {
  if (evt instanceof HttpServerUpgradeHandler.UpgradeEvent) {
    HttpServerUpgradeHandler.UpgradeEvent upgradeEvent=(HttpServerUpgradeHandler.UpgradeEvent)evt;
    this.isUpgradeH2cMode=true;
    onHeadersRead(ctx,1,http1HeadersToHttp2Headers(upgradeEvent.upgradeRequest()),0,true);
  }
  super.userEventTriggered(ctx,evt);
}
