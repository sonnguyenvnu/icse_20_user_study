private void initialize(ChannelHandlerContext ctx){
  if (LOG.isDebugEnabled()) {
    LOG.debug("Initializing autoflush handler on channel {} Cid: {}",ctx.channel(),NettyUtils.clientID(ctx.channel()));
  }
switch (state) {
case 1:
case 2:
    return;
}
state=1;
EventExecutor loop=ctx.executor();
lastWriteTime=System.nanoTime();
writerIdleTimeout=loop.schedule(new WriterIdleTimeoutTask(ctx),writerIdleTimeNanos,TimeUnit.NANOSECONDS);
}
