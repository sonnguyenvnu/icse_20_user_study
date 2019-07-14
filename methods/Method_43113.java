public BitfinexTradingFeeResponse[] getBitfinexDynamicTradingFees() throws IOException {
  try {
    BitfinexTradingFeeResponse[] response=bitfinex.tradingFees(apiKey,payloadCreator,signatureCreator,new BitfinexTradingFeesRequest(String.valueOf(exchange.getNonceFactory().createValue())));
    return response;
  }
 catch (  BitfinexException e) {
    throw new ExchangeException(e);
  }
}
