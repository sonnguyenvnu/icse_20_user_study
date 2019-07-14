protected SofaRpcException buildSerializeError(String message,Throwable throwable){
  return new SofaRpcException(getErrorCode(true),message,throwable);
}
