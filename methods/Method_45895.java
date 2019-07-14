/** 
 * @param clear ?????????
 * @return ?? Java Future ??
 * @throws SofaRpcException ?????????????
 */
public static Future getFuture(boolean clear) throws SofaRpcException {
  RpcInvokeContext context=RpcInvokeContext.getContext();
  Future future=context.getFuture();
  if (future == null) {
    throw new SofaRpcException(RpcErrorType.CLIENT_UNDECLARED_ERROR,LogCodes.getLog(LogCodes.ERROR_RESPONSE_FUTURE_NULL,Thread.currentThread()));
  }
  if (clear) {
    context.setFuture(null);
  }
  return future;
}
