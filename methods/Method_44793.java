public QuoineAccountBalance[] getQuoineAccountBalance() throws IOException {
  try {
    return quoine.getAllBalance(QUOINE_API_VERSION,signatureCreator,contentType);
  }
 catch (  HttpStatusIOException e) {
    throw new ExchangeException(e.getHttpBody(),e);
  }
}
