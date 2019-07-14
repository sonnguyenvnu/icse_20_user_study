public boolean cancelAll(CurrencyPair currencyPair) throws IOException {
  Long marketId=currencyPair == null ? null : exchange.tradePairId(currencyPair);
  CryptopiaBaseResponse<List> response=cryptopia.cancelTrade(signatureCreator,new Cryptopia.CancelTradeRequest("TradePair",null,marketId));
  return !response.getData().isEmpty();
}
