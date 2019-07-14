public static LimitOrder createOrder(CurrencyPair currencyPair,CCEXBuySellData priceAndAmount,Order.OrderType orderType){
  return new LimitOrder(orderType,priceAndAmount.getQuantity(),currencyPair,"",null,priceAndAmount.getRate());
}
