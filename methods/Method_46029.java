@Override public long catchException(Throwable t){
  if (t instanceof SofaRpcException) {
    SofaRpcException exception=(SofaRpcException)t;
    if (exception.getErrorType() == RpcErrorType.CLIENT_TIMEOUT || exception.getErrorType() == RpcErrorType.SERVER_BUSY) {
      return exceptionCount.incrementAndGet();
    }
  }
  return exceptionCount.get();
}
