public List<BitflyerDepositOrWithdrawal> getWithdrawals() throws IOException {
  try {
    return bitflyer.getWithdrawals(apiKey,exchange.getNonceFactory(),signatureCreator);
  }
 catch (  BitflyerException e) {
    throw handleError(e);
  }
}
