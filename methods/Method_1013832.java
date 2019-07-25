private void connect(){
  if (!(sessionState.get() instanceof SessionInit)) {
    logger.info("[connect] not session init state, quit");
    return;
  }
  try {
    this.endpoint=selector.nextHop();
  }
 catch (  Exception e) {
    setSessionState(new SessionClosed(this));
    logger.error("[connect] select nextHop error",e);
    throw e;
  }
  ChannelFuture connectionFuture=initChannel(endpoint);
  connectionFuture.addListener(new ChannelFutureListener(){
    @Override public void operationComplete(    ChannelFuture future) throws Exception {
      if (future.isSuccess()) {
        onChannelEstablished(future.channel());
      }
 else {
        logger.error("[tryConnect] fail to connect: {}, {}",getSessionMeta(),future.cause());
        future.channel().eventLoop().schedule(() -> connect(),selector.selectCounts(),TimeUnit.MILLISECONDS);
      }
    }
  }
);
}
