/** 
 * ??????????RPC??
 * @param e ??
 * @return RPC??
 */
protected SofaRpcException convertToRpcException(Exception e){
  SofaRpcException exception;
  if (e instanceof SofaRpcException) {
    exception=(SofaRpcException)e;
  }
 else   if (e instanceof InvokeTimeoutException) {
    exception=new SofaTimeOutException(e);
  }
 else   if (e instanceof InvokeServerBusyException) {
    exception=new SofaRpcException(RpcErrorType.SERVER_BUSY,e);
  }
 else   if (e instanceof SerializationException) {
    boolean isServer=((SerializationException)e).isServerSide();
    exception=isServer ? new SofaRpcException(RpcErrorType.SERVER_SERIALIZE,e) : new SofaRpcException(RpcErrorType.CLIENT_SERIALIZE,e);
  }
 else   if (e instanceof DeserializationException) {
    boolean isServer=((DeserializationException)e).isServerSide();
    exception=isServer ? new SofaRpcException(RpcErrorType.SERVER_DESERIALIZE,e) : new SofaRpcException(RpcErrorType.CLIENT_DESERIALIZE,e);
  }
 else   if (e instanceof ConnectionClosedException) {
    exception=new SofaRpcException(RpcErrorType.CLIENT_NETWORK,e);
  }
 else   if (e instanceof InvokeSendFailedException) {
    exception=new SofaRpcException(RpcErrorType.CLIENT_NETWORK,e);
  }
 else   if (e instanceof InvokeServerException) {
    exception=new SofaRpcException(RpcErrorType.SERVER_UNDECLARED_ERROR,e.getCause());
  }
 else {
    exception=new SofaRpcException(RpcErrorType.CLIENT_UNDECLARED_ERROR,e);
  }
  return exception;
}
