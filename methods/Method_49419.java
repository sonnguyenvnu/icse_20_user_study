private BackendException convert(Exception esException){
  if (esException instanceof InterruptedException) {
    return new TemporaryBackendException("Interrupted while waiting for response",esException);
  }
 else {
    return new PermanentBackendException("Unknown exception while executing index operation",esException);
  }
}
