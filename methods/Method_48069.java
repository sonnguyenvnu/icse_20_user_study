static BackendException convertException(Throwable e){
  if (e instanceof TimedOutException || e instanceof UnavailableException) {
    return new TemporaryBackendException(e);
  }
  return new PermanentBackendException(e);
}
