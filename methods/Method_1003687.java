private void transmit(final HttpResponseStatus responseStatus,Object body,boolean sendLastHttpContent){
  ChannelFuture channelFuture=pre(responseStatus,false);
  if (channelFuture == null) {
    ReferenceCountUtil.release(body);
    isKeepAlive=false;
    post(responseStatus);
    return;
  }
  if (sendLastHttpContent) {
    channel.write(body);
    post(responseStatus);
  }
 else {
    post(responseStatus,channel.writeAndFlush(body));
  }
}
