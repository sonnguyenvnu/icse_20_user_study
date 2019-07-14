public BitfinexFundingTradeResponse[] getBitfinexFundingHistory(String symbol,Date until,int limit_trades) throws IOException {
  BitfinexFundingTradeResponse[] fundingTrades=bitfinex.pastFundingTrades(apiKey,payloadCreator,signatureCreator,new BitfinexPastFundingTradesRequest(String.valueOf(exchange.getNonceFactory().createValue()),symbol,until,limit_trades));
  return fundingTrades;
}
