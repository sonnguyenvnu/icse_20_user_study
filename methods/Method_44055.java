private static Order.OrderType type(Map map){
  return map.get("Type").toString().equals("Buy") ? Order.OrderType.BID : Order.OrderType.ASK;
}
