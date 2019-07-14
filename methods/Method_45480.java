protected SofaRpcException buildSerializeError(String message){
  return new SofaRpcException(getErrorCode(true),message);
}
