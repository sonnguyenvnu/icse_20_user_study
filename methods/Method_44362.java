List<HuobiOrder> getHuobiOrder(String... orderIds) throws IOException {
  List<HuobiOrder> orders=new ArrayList<>();
  for (  String orderId : orderIds) {
    HuobiOrderInfoResult orderInfoResult=huobi.getOrder(orderId,exchange.getExchangeSpecification().getApiKey(),HuobiDigest.HMAC_SHA_256,2,HuobiUtils.createUTCDate(exchange.getNonceFactory()),signatureCreator);
    orders.add(checkResult(orderInfoResult));
  }
  return orders;
}
