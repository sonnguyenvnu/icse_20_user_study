public BittrexOrder getBittrexOrder(String uuid) throws IOException {
  return bittrexAuthenticated.getOrder(apiKey,signatureCreator,exchange.getNonceFactory(),uuid).getResult();
}
