public List<BinanceOrder> allOrders(CurrencyPair pair,Long orderId,Integer limit,Long recvWindow,long timestamp) throws BinanceException, IOException {
  return binance.allOrders(BinanceAdapters.toSymbol(pair),orderId,limit,recvWindow,timestamp,super.apiKey,super.signatureCreator);
}
