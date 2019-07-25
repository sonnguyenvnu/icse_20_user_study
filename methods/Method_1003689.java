@Override public Subscriber<ByteBuf> transmitter(HttpResponseStatus responseStatus){
  return new Subscriber<ByteBuf>(){
    private void cancel(){
      channel.closeFuture().removeListener(cancelOnCloseListener);
      if (done.compareAndSet(false,true)) {
        subscription.cancel();
        post(responseStatus);
      }
    }
    @Override public void onSubscribe(    Subscription subscription){
      if (subscription == null) {
        throw new NullPointerException("'subscription' is null");
      }
      if (this.subscription != null) {
        subscription.cancel();
        return;
      }
      this.subscription=subscription;
      ChannelFuture channelFuture=pre(responseStatus,true);
      if (channelFuture == null) {
        subscription.cancel();
        isKeepAlive=false;
        notifyListeners(responseStatus);
      }
 else {
        channelFuture.addListener(f -> {
          if (f.isSuccess() && channel.isOpen()) {
            channel.closeFuture().addListener(cancelOnCloseListener);
            if (channel.isWritable()) {
              this.subscription.request(1);
            }
            onWritabilityChanged=() -> {
              if (channel.isWritable() && !done.get()) {
                this.subscription.request(1);
              }
            }
;
          }
 else {
            cancel();
          }
        }
);
      }
    }
    @Override public void onNext(    ByteBuf o){
      o.touch();
      if (channel.isOpen()) {
        channel.writeAndFlush(new DefaultHttpContent(o)).addListener(cancelOnFailure);
        if (channel.isWritable()) {
          subscription.request(1);
        }
      }
 else {
        o.release();
        cancel();
      }
    }
    @Override public void onError(    Throwable t){
      if (t == null) {
        throw new NullPointerException("error is null");
      }
      LOGGER.warn("Exception thrown transmitting stream",t);
      if (done.compareAndSet(false,true)) {
        channel.closeFuture().removeListener(cancelOnCloseListener);
        post(responseStatus);
      }
    }
    @Override public void onComplete(){
      if (done.compareAndSet(false,true)) {
        channel.closeFuture().removeListener(cancelOnCloseListener);
        post(responseStatus);
      }
    }
  }
;
}
