public BTCMarketsFundtransferHistoryResponse fundtransferHistory() throws IOException {
  BTCMarketsFundtransferHistoryResponse response=btcm.fundtransferHistory(exchange.getExchangeSpecification().getApiKey(),nonceFactory,signer);
  if (!response.getSuccess()) {
    throw new ExchangeException("failed to retrieve fundtransfer history: " + response.getErrorMessage() + " " + response.getErrorCode());
  }
  return response;
}
