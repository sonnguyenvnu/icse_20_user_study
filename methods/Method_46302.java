@Override public void onHeadersRead(ChannelHandlerContext ctx,int streamId,Http2Headers headers,int padding,boolean endOfStream){
  if (this.isUpgradeH2cMode && streamId > 1 || !this.isUpgradeH2cMode && streamId > 0) {
    if (endOfStream) {
      handleRequest(ctx,streamId,headers,null);
    }
 else {
      connection().stream(streamId).setProperty(headerKey,headers);
    }
  }
}
