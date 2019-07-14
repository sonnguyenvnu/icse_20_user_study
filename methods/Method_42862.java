@Override public String placeLimitOrder(LimitOrder limitOrder) throws IOException {
  AbucoinsCreateLimitOrderRequest req=AbucoinsAdapters.adaptAbucoinsCreateLimitOrderRequest(limitOrder);
  AbucoinsCreateOrderResponse resp=createAbucoinsOrder(req);
  if (resp.getMessage() != null)   throw new ExchangeException(resp.getMessage());
  return resp.getId();
}
