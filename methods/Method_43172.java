public List<BitflyerBalance> getBitflyerBalances() throws IOException {
  try {
    return bitflyer.getBalances(apiKey,exchange.getNonceFactory(),signatureCreator);
  }
 catch (  BitflyerException e) {
    throw handleError(e);
  }
}
