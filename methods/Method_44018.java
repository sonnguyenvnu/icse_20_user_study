public CryptoFacilitiesFills getCryptoFacilitiesFills(Date lastFillTime) throws IOException {
  CryptoFacilitiesFills fills=cryptoFacilities.fills(exchange.getExchangeSpecification().getApiKey(),signatureCreator,exchange.getNonceFactory(),Util.format(lastFillTime));
  if (fills.isSuccess()) {
    return fills;
  }
 else {
    throw new ExchangeException("Error getting CF fills: " + fills.getError());
  }
}
