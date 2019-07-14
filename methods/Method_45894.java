/** 
 * get response <p> If remoting get exception, framework will wrapped it to SofaRpcException
 * @param timeout get timeout
 * @param clear   true: framework will clear the ThreadLocal when return
 * @return The response 
 * @throws SofaRpcException When throw SofaRpcException
 * @throws InterruptedException if any thread has interrupted the current thread. The <i>interrupted status</i> of the current thread is cleared when this exception is thrown.
 */
public static Object getResponse(long timeout,boolean clear) throws SofaRpcException, InterruptedException {
  RpcInvokeContext context=RpcInvokeContext.getContext();
  Future future=context.getFuture();
  if (null == future) {
    throw new SofaRpcException(RpcErrorType.CLIENT_UNDECLARED_ERROR,LogCodes.getLog(LogCodes.ERROR_RESPONSE_FUTURE_NULL,Thread.currentThread()));
  }
  try {
    if (clear) {
      context.setFuture(null);
    }
    return future.get(timeout,TimeUnit.MILLISECONDS);
  }
 catch (  TimeoutException ex) {
    if (!future.isDone()) {
      throw new SofaTimeOutException("Future is not done when timeout.",ex);
    }
 else {
      throw new SofaTimeOutException(ex.getMessage(),ex);
    }
  }
catch (  ExecutionException ex) {
    Throwable cause=ex.getCause();
    if (cause instanceof SofaRpcException) {
      throw (SofaRpcException)cause;
    }
 else {
      throw new SofaRpcException(RpcErrorType.SERVER_UNDECLARED_ERROR,cause.getMessage(),cause);
    }
  }
}
