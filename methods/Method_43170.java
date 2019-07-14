public List<BitflyerDepositOrWithdrawal> getCashDeposits() throws IOException {
  try {
    return bitflyer.getCashDeposits(apiKey,exchange.getNonceFactory(),signatureCreator);
  }
 catch (  BitflyerException e) {
    throw handleError(e);
  }
}
