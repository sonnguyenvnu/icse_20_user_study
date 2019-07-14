HuobiOrder[] getHuobiTradeHistory(TradeHistoryParams tradeHistoryParams) throws IOException {
  String tradeStates="partial-filled,partial-canceled,filled";
  HuobiOrdersResult result=huobi.getOpenOrders(tradeStates,exchange.getExchangeSpecification().getApiKey(),HuobiDigest.HMAC_SHA_256,2,HuobiUtils.createUTCDate(exchange.getNonceFactory()),signatureCreator);
  return checkResult(result);
}
