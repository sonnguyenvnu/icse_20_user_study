public List<LimitOrder> getAllOpenOrders() throws IOException {
  LivecoinPaginatedResponse response=service.allClientOrders(apiKey,signatureCreator,"OPEN");
  List<LimitOrder> resp=new ArrayList<>();
  if (response.getData() == null)   return resp;
  for (  Map map : response.getData()) {
    Object statusRaw=map.get("orderStatus");
    if (statusRaw != null && (statusRaw.toString().equals("OPEN") || statusRaw.toString().equals("PARTIALLY_FILLED"))) {
      resp.add(LivecoinAdapters.adaptOpenOrder(map));
    }
  }
  return resp;
}
