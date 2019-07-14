public static int getErrorCode(Throwable throwable){
  if (throwable instanceof HttpException) {
    return ((HttpException)throwable).code();
  }
  return -1;
}
