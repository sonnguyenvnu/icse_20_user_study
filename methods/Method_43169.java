public List<BitflyerCoinHistory> getCoinIns() throws IOException {
  try {
    return bitflyer.getCoinIns(apiKey,exchange.getNonceFactory(),signatureCreator);
  }
 catch (  BitflyerException e) {
    throw handleError(e);
  }
}
