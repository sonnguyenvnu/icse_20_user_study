public IndependentReserveOrderDetailsResponse getOrderDetails(String orderGuid) throws IOException {
  Long nonce=exchange.getNonceFactory().createValue();
  String apiKey=exchange.getExchangeSpecification().getApiKey();
  IndependentReserveOrderDetailsRequest request=new IndependentReserveOrderDetailsRequest(apiKey,nonce,orderGuid);
  request.setSignature(signatureCreator.digestParamsToString(ExchangeEndpoint.GET_ORDER_DETAILS,nonce,request.getParameters()));
  return independentReserveAuthenticated.orderDetails(request);
}
