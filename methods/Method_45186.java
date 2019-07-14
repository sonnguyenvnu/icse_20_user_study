private void closeIfNotKeepAlive(final FullHttpRequest request,final ChannelFuture future){
  if (!isKeepAlive(request)) {
    future.addListener(ChannelFutureListener.CLOSE);
  }
}
