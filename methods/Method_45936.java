protected SofaRpcException buildDeserializeError(String message){
  return new SofaRpcException(getErrorCode(false),message);
}
