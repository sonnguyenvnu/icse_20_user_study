protected RuntimeException handleHttpError(HttpStatusIOException exception) throws IOException {
  if (exception.getHttpStatusCode() == 304) {
    return new NonceException(exception.getHttpBody());
  }
 else {
    throw exception;
  }
}
