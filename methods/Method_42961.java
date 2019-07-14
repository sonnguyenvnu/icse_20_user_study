public static OrderType convertType(boolean isBuyer){
  return isBuyer ? OrderType.BID : OrderType.ASK;
}
