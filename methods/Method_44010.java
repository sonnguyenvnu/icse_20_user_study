public CryptoFacilitiesTickers getCryptoFacilitiesTickers() throws IOException {
  CryptoFacilitiesTickers tickers=cryptoFacilities.getTickers();
  if (tickers.isSuccess()) {
    return tickers;
  }
 else {
    throw new ExchangeException("Error getting CF tickers: " + tickers.getError());
  }
}
