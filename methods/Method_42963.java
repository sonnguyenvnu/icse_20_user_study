private static Ticker adaptPriceQuantity(BinancePriceQuantity priceQuantity){
  return new Ticker.Builder().currencyPair(adaptSymbol(priceQuantity.symbol)).ask(priceQuantity.askPrice).askSize(priceQuantity.askQty).bid(priceQuantity.bidPrice).bidSize(priceQuantity.bidQty).build();
}
