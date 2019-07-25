@Override public void destroy(final Channel channel,final boolean error,final Callback<Channel> channelCallback){
  if (channel.isOpen()) {
    channel.close().addListener((ChannelFutureListener)channelFuture -> {
      if (channelFuture.isSuccess()) {
        channelCallback.onSuccess(channelFuture.channel());
      }
 else {
        final Throwable cause=channelFuture.cause();
        LOG.error("Failed to destroy channel, remote={}",_remoteAddress,cause);
        channelCallback.onError(HttpNettyStreamClient.toException(cause));
      }
    }
);
  }
 else {
    channelCallback.onSuccess(channel);
  }
}
