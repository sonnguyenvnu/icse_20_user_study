@Override public Collection<Order> getOrder(OrderQueryParams... orderQueryParams) throws IOException {
  Collection<Order> result=new ArrayList<>(orderQueryParams.length);
  for (  OrderQueryParams p : orderQueryParams) {
    Bl3pOrderQueryParams bp=(Bl3pOrderQueryParams)p;
    Bl3pGetOrder order=this.bl3p.getOrder(apiKey,signatureCreator,nonceFactory,Bl3pUtils.toPairString(bp.getCurrencyPair()),bp.getOrderId());
    result.add(Bl3pAdapters.adaptGetOrder(bp.getCurrencyPair(),order.getData()));
  }
  return result;
}
