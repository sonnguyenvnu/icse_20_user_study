private void post(HttpResponseStatus responseStatus,ChannelFuture lastContentFuture){
  lastContentFuture.addListener(v -> drainRequestBody(e -> {
    if (LOGGER.isWarnEnabled()) {
      if (e instanceof RequestBodyTooLargeException) {
        LOGGER.warn("Unread request body was too large to drain, will close connection (maxContentLength: {})",((RequestBodyTooLargeException)e).getMaxContentLength());
      }
 else       if (e != null) {
        LOGGER.warn("An error occurred draining the unread request body. The connection will be closed",e);
      }
    }
    if (channel.isOpen()) {
      if (isKeepAlive && e == null) {
        lastContentFuture.channel().read();
        ConnectionIdleTimeout.of(channel).reset();
      }
 else {
        lastContentFuture.channel().close();
      }
    }
    notifyListeners(responseStatus);
  }
));
}
