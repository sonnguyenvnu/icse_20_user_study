@Override public String placeLimitOrder(LimitOrder limitOrder) throws IOException {
  BaseYoBitResponse response=trade(limitOrder);
  return response.returnData.get("order_id").toString();
}
