@Override public void doOnResponse(Object result){
  if (rpcFuture == null) {
    return;
  }
  ClassLoader oldCl=Thread.currentThread().getContextClassLoader();
  SofaResponse response=(SofaResponse)result;
  try {
    Thread.currentThread().setContextClassLoader(this.classLoader);
    RpcInternalContext.setContext(context);
    decode(response);
    rpcFuture.setSuccess(response);
  }
  finally {
    Thread.currentThread().setContextClassLoader(oldCl);
    RpcInvokeContext.removeContext();
    RpcInternalContext.removeAllContext();
  }
}
