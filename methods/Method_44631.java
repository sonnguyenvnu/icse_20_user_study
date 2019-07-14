private static OrderType toOrderType(String mercadoType){
  return mercadoType.equals("buy") ? OrderType.BID : OrderType.ASK;
}
