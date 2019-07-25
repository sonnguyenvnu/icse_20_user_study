private void initialize(ChannelHandlerContext ctx){
  if (LOG.isDebugEnabled()) {
    LOG.debug("Initializing autoflush handler on channel {}",ctx.channel());
  }
switch (state) {
case 1:
case 2:
    return;
}
state=1;
EventExecutor loop=ctx.executor();
lastExecutionTime=System.nanoTime();
resenderTimeout=loop.schedule(new WriterIdleTimeoutTask(ctx),resenderTimeNanos,TimeUnit.NANOSECONDS);
}
