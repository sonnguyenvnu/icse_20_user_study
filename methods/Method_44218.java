public static String convertByType(OrderType orderType){
  return OrderType.BID.equals(orderType) ? IConstants.BUY : IConstants.SELL;
}
