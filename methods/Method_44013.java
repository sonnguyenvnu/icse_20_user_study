public CryptoFacilitiesPublicFills getCryptoFacilitiesHistory(CurrencyPair currencyPair) throws IOException {
  CryptoFacilitiesPublicFills publicFills=cryptoFacilities.getHistory(currencyPair.base.toString());
  if (publicFills.isSuccess()) {
    publicFills.setCurrencyPair(currencyPair);
    return publicFills;
  }
 else {
    throw new ExchangeException("Error getting CF public fills: " + publicFills.getError());
  }
}
