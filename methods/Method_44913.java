public VaultoroNewOrderResponse placeMarketOrder(CurrencyPair currencyPair,OrderType orderType,BigDecimal amount) throws IOException {
  return placeOrder("market",currencyPair,orderType,amount,null);
}
