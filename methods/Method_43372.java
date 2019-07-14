public boolean cancelBittrexLimitOrder(String uuid) throws IOException {
  bittrexAuthenticated.cancel(apiKey,signatureCreator,exchange.getNonceFactory(),uuid);
  return true;
}
