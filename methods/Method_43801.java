private static Date getTimestamp(List<CoingiTicker> tickers){
  if (tickers.get(0).getTimestamp() == null) {
    return null;
  }
  return new Date(tickers.get(0).getTimestamp() * 1000);
}
