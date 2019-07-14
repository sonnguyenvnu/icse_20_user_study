public List<DVChainTrade> getOrders() throws IOException {
  try {
    DVChainTradesResponse tradesResponse=dvChain.getTrades(authToken,"no-cache","no-cache");
    return tradesResponse.getData().stream().filter(t -> t.getStatus().equals("Open")).collect(Collectors.toList());
  }
 catch (  DVChainException e) {
    throw handleException(e);
  }
}
