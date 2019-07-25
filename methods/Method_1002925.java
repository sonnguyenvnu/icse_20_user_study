@Override public Future<Void> shutdown(){
  isShutdown=true;
  return GlobalEventExecutor.INSTANCE.newSucceededFuture(null);
}
