public static List<LimitOrder> createOrders(final CurrencyPair currencyPair,final OrderType orderType,final List<RippleOrder> orders,final String baseCounterparty,final String counterCounterparty){
  final List<LimitOrder> limitOrders=new ArrayList<>();
  for (  final RippleOrder rippleOrder : orders) {
    final BigDecimal originalAmount;
    if (orderType == OrderType.BID) {
      originalAmount=rippleOrder.getTakerPaysFunded().getValue();
    }
 else {
      originalAmount=rippleOrder.getTakerGetsFunded().getValue();
    }
    final BigDecimal price=rippleOrder.getPrice().getValue();
    final RippleLimitOrder order=new RippleLimitOrder(orderType,originalAmount,currencyPair,Integer.toString(rippleOrder.getSequence()),null,price,baseCounterparty,counterCounterparty);
    limitOrders.add(order);
  }
  return limitOrders;
}
