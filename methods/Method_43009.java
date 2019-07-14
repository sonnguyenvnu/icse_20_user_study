public void testNewOrder(CurrencyPair pair,OrderSide side,OrderType type,TimeInForce timeInForce,BigDecimal quantity,BigDecimal price,String newClientOrderId,BigDecimal stopPrice,BigDecimal icebergQty,Long recvWindow,long timestamp) throws IOException, BinanceException {
  binance.testNewOrder(BinanceAdapters.toSymbol(pair),side,type,timeInForce,quantity,price,newClientOrderId,stopPrice,icebergQty,recvWindow,timestamp,super.apiKey,super.signatureCreator);
}
