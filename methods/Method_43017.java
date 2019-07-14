private static UserTrade createUserTrade(BitbayOrder bitbayOrder){
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
  return new UserTrade(type,bitbayOrder.getAmount(),currencyPair,bitbayOrder.getCurrentPrice().divide(bitbayOrder.getStartAmount()),date,String.valueOf(bitbayOrder.getId()),String.valueOf(bitbayOrder.getId()),null,null);
}
