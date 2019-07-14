private BTCTurkExchangeResult postExchange(BigDecimal total_amount,BigDecimal price,BigDecimal triggerPrice,CurrencyPair pair,BTCTurkOrderMethods orderMethod,BTCTurkOrderTypes orderTypes) throws IOException {
  String[] _zero=getLocalizedBigDecimalValue(BigDecimal.ZERO).split("\\.");
  String[] _price=_zero;
  if (orderMethod.equals(BTCTurkOrderMethods.LIMIT))   _price=getLocalizedBigDecimalValue(price).split("\\.");
  String[] _triggerPrice=_zero;
  if (orderMethod.equals(BTCTurkOrderMethods.STOP_LIMIT) || orderMethod.equals(BTCTurkOrderMethods.STOP_MARKET))   _triggerPrice=getLocalizedBigDecimalValue(triggerPrice).split("\\.");
  String[] _total=getLocalizedBigDecimalValue(total_amount).split("\\.");
  String[] _amount=_zero;
  if (orderTypes.equals(BTCTurkOrderTypes.Sell)) {
    _amount=getLocalizedBigDecimalValue(total_amount).split("\\.");
    _total=_zero;
  }
  BTCTurkOrder order=new BTCTurkOrder(orderMethod,_price[0],_price[1],_amount[0],_amount[1],_total[0],_total[1],2,_triggerPrice[0],_triggerPrice[1],orderTypes,new BTCTurkPair(pair));
  return btcTurk.setOrder(order.getPrice(),order.getPricePrecision(),order.getAmount(),order.getAmountPrecision(),order.getOrderType().getValue(),order.getOrderMethod().getValue(),order.getPairSymbol().toString(),order.getDenominatorPrecision(),order.getTotal(),order.getTotalPrecision(),order.getTriggerPrice(),order.getTriggerPricePrecision(),exchange.getExchangeSpecification().getApiKey(),exchange.getNonceFactory(),signatureCreator);
}
