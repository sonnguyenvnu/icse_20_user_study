public static ErrorCause fromThrowable(Throwable throwable){
  if (throwable instanceof ProgressException)   return ((ProgressException)throwable).getError();
 else   return new ErrorCause(throwable.getMessage());
}
