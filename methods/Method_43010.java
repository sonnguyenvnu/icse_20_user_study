public BinanceOrder orderStatus(CurrencyPair pair,long orderId,String origClientOrderId,Long recvWindow,long timestamp) throws IOException, BinanceException {
  return binance.orderStatus(BinanceAdapters.toSymbol(pair),orderId,origClientOrderId,recvWindow,timestamp,super.apiKey,super.signatureCreator);
}
