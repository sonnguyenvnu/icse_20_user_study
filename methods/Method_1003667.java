@Override public void connect(final Downstream<? super T> downstream) throws Exception {
  channelPool.acquire().addListener(acquireFuture -> {
    if (acquireFuture.isSuccess()) {
      Channel channel=(Channel)acquireFuture.getNow();
      if (channel.eventLoop().equals(execution.getEventLoop())) {
        send(downstream,channel);
      }
 else {
        channel.deregister().addListener(deregisterFuture -> execution.getEventLoop().register(channel).addListener(registerFuture -> {
          if (registerFuture.isSuccess()) {
            send(downstream,channel);
          }
 else {
            channel.close();
            channelPool.release(channel);
            connectFailure(downstream,registerFuture.cause());
          }
        }
));
      }
    }
 else {
      connectFailure(downstream,acquireFuture.cause());
    }
  }
);
}
