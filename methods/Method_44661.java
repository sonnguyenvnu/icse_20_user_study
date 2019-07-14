public static LimitOrder convert(OkexOpenOrder o){
  return new LimitOrder.Builder(o.getSide() == Side.sell ? OrderType.ASK : OrderType.BID,toPair(o.getInstrumentId())).id(o.getOrderId()).limitPrice(o.getPrice()).originalAmount(o.getSize()).timestamp(o.getCreatedAt()).build();
}
