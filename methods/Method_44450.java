public Map<String,KrakenLedger> queryKrakenLedger(String... ledgerIds) throws IOException {
  KrakenQueryLedgerResult ledgerResult=kraken.queryLedgers(createDelimitedString(ledgerIds),exchange.getExchangeSpecification().getApiKey(),signatureCreator,exchange.getNonceFactory());
  return checkResult(ledgerResult);
}
