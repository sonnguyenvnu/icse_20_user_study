@Override public void visit(FailureLogMessage logMessage){
  throw new ProxyWorkerException(logMessage.exceptionType(),logMessage.message(),logMessage.stackTrace());
}
