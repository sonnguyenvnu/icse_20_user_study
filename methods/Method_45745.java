public static boolean isClientException(SofaRpcException exception){
  int errorType=exception.getErrorType();
  return errorType >= 200 && errorType < 300;
}
