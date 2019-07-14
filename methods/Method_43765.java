List<CoindirectOrder> listExchangeOrders(String symbol,boolean completed,long offset,long max) throws IOException, CoindirectException {
  return coindirect.listExchangeOrders(symbol,completed,offset,max,signatureCreator);
}
