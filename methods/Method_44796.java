protected RuntimeException handleHttpError(HttpStatusIOException exception) throws IOException {
  throw new ExchangeException(exception.getHttpBody(),exception);
}
