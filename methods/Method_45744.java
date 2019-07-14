public static boolean isServerException(SofaRpcException exception){
  int errorType=exception.getErrorType();
  return errorType >= 100 && errorType < 200;
}
