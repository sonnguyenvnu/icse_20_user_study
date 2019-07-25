@Override public void write(final ChannelHandlerContext ctx,final Object msg,final ChannelPromise promise) throws Exception {
  if (msg instanceof HttpPipelinedResponse) {
    final HttpPipelinedResponse current=(HttpPipelinedResponse)msg;
    assert current.promise() == promise;
    boolean channelShouldClose=false;
synchronized (holdingQueue) {
      if (holdingQueue.size() < maxEventsHeld) {
        holdingQueue.add(current);
        while (!holdingQueue.isEmpty()) {
          final HttpPipelinedResponse top=holdingQueue.peek();
          if (top.sequence() != writeSequence) {
            break;
          }
          holdingQueue.remove();
          ctx.write(top.response(),top.promise());
          writeSequence++;
        }
      }
 else {
        channelShouldClose=true;
      }
    }
    if (channelShouldClose) {
      try {
        Netty4Utils.closeChannels(Collections.singletonList(ctx.channel()));
      }
  finally {
        current.release();
        promise.setSuccess();
      }
    }
  }
 else {
    ctx.write(msg,promise);
  }
}
