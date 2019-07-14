public Map<String,KrakenTicker> getKrakenTickers(CurrencyPair... currencyPairs) throws IOException {
  KrakenTickerResult tickerResult=kraken.getTicker(delimitAssetPairs(currencyPairs));
  return checkResult(tickerResult);
}
