public static OpenOrders adaptOrders(BitfinexOrderStatusResponse[] activeOrders){
  List<LimitOrder> limitOrders=new ArrayList<>();
  List<Order> hiddenOrders=new ArrayList<>();
  for (  BitfinexOrderStatusResponse order : activeOrders) {
    OrderType orderType=order.getSide().equalsIgnoreCase("buy") ? OrderType.BID : OrderType.ASK;
    OrderStatus status=adaptOrderStatus(order);
    CurrencyPair currencyPair=adaptCurrencyPair(order.getSymbol());
    Date timestamp=convertBigDecimalTimestampToDate(order.getTimestamp());
    Supplier<LimitOrder> limitOrderCreator=() -> new LimitOrder(orderType,order.getOriginalAmount(),currencyPair,String.valueOf(order.getId()),timestamp,order.getPrice(),order.getAvgExecutionPrice(),order.getExecutedAmount(),null,status);
    Supplier<StopOrder> stopOrderCreator=() -> new StopOrder(orderType,order.getOriginalAmount(),currencyPair,String.valueOf(order.getId()),timestamp,order.getPrice(),null,order.getAvgExecutionPrice(),order.getExecutedAmount(),status);
    LimitOrder limitOrder=null;
    StopOrder stopOrder=null;
    Optional<BitfinexOrderType> bitfinexOrderType=Arrays.stream(BitfinexOrderType.values()).filter(v -> v.getValue().equals(order.getType())).findFirst();
    if (bitfinexOrderType.isPresent()) {
switch (bitfinexOrderType.get()) {
case FILL_OR_KILL:
        limitOrder=limitOrderCreator.get();
      limitOrder.addOrderFlag(BitfinexOrderFlags.FILL_OR_KILL);
    break;
case MARGIN_FILL_OR_KILL:
  limitOrder=limitOrderCreator.get();
limitOrder.addOrderFlag(BitfinexOrderFlags.FILL_OR_KILL);
limitOrder.addOrderFlag(BitfinexOrderFlags.MARGIN);
break;
case MARGIN_LIMIT:
limitOrder=limitOrderCreator.get();
limitOrder.addOrderFlag(BitfinexOrderFlags.MARGIN);
break;
case MARGIN_STOP:
stopOrder=stopOrderCreator.get();
stopOrder.addOrderFlag(BitfinexOrderFlags.STOP);
stopOrder.addOrderFlag(BitfinexOrderFlags.MARGIN);
break;
case MARGIN_STOP_LIMIT:
stopLimitWarning();
stopOrder=stopOrderCreator.get();
stopOrder.addOrderFlag(BitfinexOrderFlags.STOP);
stopOrder.addOrderFlag(BitfinexOrderFlags.MARGIN);
break;
case MARGIN_TRAILING_STOP:
limitOrder=limitOrderCreator.get();
limitOrder.addOrderFlag(BitfinexOrderFlags.TRAILING_STOP);
limitOrder.addOrderFlag(BitfinexOrderFlags.MARGIN);
break;
case STOP:
stopOrder=stopOrderCreator.get();
stopOrder.addOrderFlag(BitfinexOrderFlags.STOP);
break;
case STOP_LIMIT:
stopLimitWarning();
stopOrder=stopOrderCreator.get();
stopOrder.addOrderFlag(BitfinexOrderFlags.STOP);
break;
case TRAILING_STOP:
limitOrder=limitOrderCreator.get();
limitOrder.addOrderFlag(BitfinexOrderFlags.TRAILING_STOP);
break;
case LIMIT:
limitOrder=limitOrderCreator.get();
break;
case MARGIN_MARKET:
case MARKET:
log.warn("Unexpected market order on book. Defaulting to limit order");
limitOrder=limitOrderCreator.get();
break;
default :
log.warn("Unhandled Bitfinex order type [{}]. Defaulting to limit order",order.getType());
limitOrder=limitOrderCreator.get();
break;
}
}
 else {
log.warn("Unknown Bitfinex order type [{}]. Defaulting to limit order",order.getType());
limitOrder=limitOrderCreator.get();
}
if (limitOrder != null) {
limitOrders.add(limitOrder);
}
 else if (stopOrder != null) {
hiddenOrders.add(stopOrder);
}
}
return new OpenOrders(limitOrders,hiddenOrders);
}
