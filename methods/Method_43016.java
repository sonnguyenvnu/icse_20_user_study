private static LimitOrder createOrder(BitbayOrder bitbayOrder){
  CurrencyPair currencyPair=new CurrencyPair(bitbayOrder.getCurrency(),bitbayOrder.getPaymentCurrency());
  OrderType type="ask".equals(bitbayOrder.getType()) ? OrderType.ASK : OrderType.BID;
  DateFormat formatter=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
  Date date;
  try {
    date=formatter.parse(bitbayOrder.getDate());
  }
 catch (  ParseException e) {
    throw new IllegalArgumentException(e);
  }
  return new LimitOrder(type,bitbayOrder.getAmount(),currencyPair,String.valueOf(bitbayOrder.getId()),date,bitbayOrder.getStartPrice().divide(bitbayOrder.getStartAmount()));
}
