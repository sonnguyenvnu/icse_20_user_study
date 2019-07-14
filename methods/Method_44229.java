public String placeLimitOrder(String symbol,BigDecimal amount,BigDecimal price,FCoinSide side) throws IOException {
  FCoinOrder order=new FCoinOrder(symbol,side,FCoinType.limit,price,amount);
  FCoinOrderResult result=fcoin.placeOrder(apiKey,System.currentTimeMillis(),signatureCreator,order);
  if (result.getStatus() == 0) {
    return result.getData();
  }
 else {
    throw new ExchangeException("Error status: " + result.getStatus() + ", " + result.getMsg());
  }
}
