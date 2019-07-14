public static String adaptOrderId(KrakenOrderResponse orderResponse){
  List<String> orderIds=orderResponse.getTransactionIds();
  return (orderIds == null || orderIds.isEmpty()) ? "" : orderIds.get(0);
}
