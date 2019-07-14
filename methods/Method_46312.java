@Override public void doOnException(Throwable e){
  if (rpcFuture == null) {
    return;
  }
  ClassLoader cl=Thread.currentThread().getContextClassLoader();
  try {
    Thread.currentThread().setContextClassLoader(this.classLoader);
    RpcInternalContext.setContext(context);
    rpcFuture.setFailure(e);
  }
  finally {
    Thread.currentThread().setContextClassLoader(cl);
    RpcInvokeContext.removeContext();
    RpcInternalContext.removeAllContext();
  }
}
