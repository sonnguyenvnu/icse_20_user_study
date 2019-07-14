public OrderPlacementResponse placeAnOrder(OrderPlacementRequest req) throws IOException {
  OrderPlacementResponse res=okex.placeAnOrder(apikey,digest,timestamp(),passphrase,req);
  res.checkResult();
  return res;
}
