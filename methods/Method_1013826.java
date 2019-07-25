public ChannelFuture close(final ChannelPromise promise){
  ChannelHandlerContext ctx=ctx();
  EventExecutor executor=ctx.executor();
  if (executor.inEventLoop()) {
    return finishEncode(ctx,promise);
  }
 else {
    executor.execute(new Runnable(){
      @Override public void run(){
        ChannelFuture f=finishEncode(ctx(),promise);
        f.addListener(new ChannelPromiseNotifier(promise));
      }
    }
);
    return promise;
  }
}
