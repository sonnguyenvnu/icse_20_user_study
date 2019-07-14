public Map getWithdrawals(String accountId) throws IOException {
  String apiKey=exchange.getExchangeSpecification().getApiKey();
  BigDecimal timestamp=coinbase.getTime(Coinbase.CB_VERSION_VALUE).getData().getEpoch();
  return coinbase.getWithdrawals(Coinbase.CB_VERSION_VALUE,apiKey,signatureCreator2,timestamp,accountId);
}
