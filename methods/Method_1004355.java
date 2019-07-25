@Override public void dispatch(long startOffset,ByteBuf body){
  final long currentTime=System.currentTimeMillis();
  try {
    log.appendData(startOffset,body);
  }
 catch (  Exception e) {
    LOG.error("index log dispatch failed.",e);
    Metrics.counter("indexDispatchError").inc();
  }
 finally {
    Metrics.timer("indexLogDispatcher").update(System.currentTimeMillis() - currentTime,TimeUnit.MILLISECONDS);
  }
}
