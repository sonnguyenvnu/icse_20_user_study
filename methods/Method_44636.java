public MercadoBitcoinOrderBook getMercadoBitcoinOrderBook(CurrencyPair currencyPair) throws IOException {
  MercadoBitcoinUtils.verifyCurrencyPairAvailability(currencyPair);
  return mercadoBitcoin.getOrderBook(currencyPair.base.getSymbol());
}
