public static String adaptOrderId(BitmexOrderResponse orderResponse){
  List<String> orderIds=orderResponse.getTransactionIds();
  return (orderIds == null || orderIds.isEmpty()) ? "" : orderIds.get(0);
}
