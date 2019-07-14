public CryptoFacilitiesOrderBook getCryptoFacilitiesOrderBook(CurrencyPair currencyPair) throws IOException {
  CryptoFacilitiesOrderBook orderBook=cryptoFacilities.getOrderBook(currencyPair.base.toString());
  if (orderBook.isSuccess()) {
    orderBook.setCurrencyPair(currencyPair);
    return orderBook;
  }
 else {
    throw new ExchangeException("Error getting CF order book: " + orderBook.getError());
  }
}
