public CryptoFacilitiesTicker getTicker(String symbol){
  if (isSuccess() && tickers != null) {
    for (    CryptoFacilitiesTicker ticker : tickers) {
      if (ticker != null && ticker.getSymbol().equalsIgnoreCase(symbol)) {
        return ticker;
      }
    }
  }
  return null;
}
