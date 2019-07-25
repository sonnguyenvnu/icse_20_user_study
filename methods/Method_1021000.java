@Override public void fatal(String message,Throwable t){
  JbootExceptionHolder.hold(message,t);
  logger.error(message,t);
}
