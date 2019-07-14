protected SofaRpcException buildDeserializeError(String message,Throwable throwable){
  return new SofaRpcException(getErrorCode(false),message,throwable);
}
