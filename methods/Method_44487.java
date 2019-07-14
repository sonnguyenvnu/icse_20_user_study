private static String adaptSide(OrderType type){
  return type.equals(ASK) ? "sell" : "buy";
}
