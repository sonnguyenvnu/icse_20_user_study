public KrakenTradeVolume getTradeVolume(CurrencyPair... currencyPairs) throws IOException {
  KrakenTradeVolumeResult result=kraken.tradeVolume(delimitAssetPairs(currencyPairs),exchange.getExchangeSpecification().getApiKey(),signatureCreator,exchange.getNonceFactory());
  return checkResult(result);
}
