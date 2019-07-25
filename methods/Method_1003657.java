public void error(Throwable throwable){
  if (throwable instanceof ClientErrorException) {
    clientError(((ClientErrorException)throwable).getClientErrorCode());
    return;
  }
  ServerErrorHandler serverErrorHandler=get(ServerErrorHandler.TYPE);
  throwable=unpackThrowable(throwable);
  ThrowableHolder throwableHolder=getRequest().maybeGet(ThrowableHolder.TYPE).orElse(null);
  if (throwableHolder == null) {
    getRequest().add(ThrowableHolder.TYPE,new ThrowableHolder(throwable));
    try {
      if (throwable instanceof InvalidPathEncodingException) {
        serverErrorHandler.error(this,(InvalidPathEncodingException)throwable);
      }
 else {
        serverErrorHandler.error(this,throwable);
      }
    }
 catch (    Throwable errorHandlerThrowable) {
      onErrorHandlerError(serverErrorHandler,throwable,errorHandlerThrowable);
    }
  }
 else {
    onErrorHandlerError(serverErrorHandler,throwableHolder.getThrowable(),throwable);
  }
}
