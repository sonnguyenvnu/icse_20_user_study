/** 
 * ??????????RPC??
 * @param e ????????
 * @return RPC??
 */
protected SofaRpcException convertToRpcException(InvocationTargetException e){
  SofaRpcException exception;
  Throwable ie=e.getCause();
  if (ie != null) {
    Throwable realException=ie.getCause();
    if (realException != null) {
      if (realException instanceof SocketTimeoutException) {
        exception=new SofaRpcException(RpcErrorType.CLIENT_TIMEOUT,"Client read timeout!",realException);
      }
 else       if (realException instanceof ConnectException) {
        open=false;
        exception=new SofaRpcException(RpcErrorType.CLIENT_NETWORK,"Connect to remote " + transportConfig.getProviderInfo() + " error!",realException);
      }
 else {
        exception=new SofaRpcException(RpcErrorType.CLIENT_UNDECLARED_ERROR,"Send message to remote catch error: " + realException.getMessage(),realException);
      }
    }
 else {
      exception=new SofaRpcException(RpcErrorType.CLIENT_UNDECLARED_ERROR,"Send message to remote catch error: " + ie.getMessage(),ie);
    }
  }
 else {
    exception=new SofaRpcException(RpcErrorType.CLIENT_UNDECLARED_ERROR,"Send message to remote catch error: " + e.getMessage(),e);
  }
  return exception;
}
