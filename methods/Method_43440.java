public List<BluetradeExecutedTrade> getTrades(TradeHistoryParams params) throws IOException {
  String market=null;
  String orderStatus=null;
  String orderType=null;
  Integer limit=null;
  BleutradeTradeHistoryParams bleutradeTradeHistoryParams;
  if (params instanceof BleutradeTradeHistoryParams) {
    bleutradeTradeHistoryParams=(BleutradeTradeHistoryParams)params;
    market=bleutradeTradeHistoryParams.market;
    orderStatus=bleutradeTradeHistoryParams.orderStatus;
    orderType=bleutradeTradeHistoryParams.orderType;
    limit=bleutradeTradeHistoryParams.getLimit();
  }
  if (params instanceof TradeHistoryParamLimit) {
    TradeHistoryParamLimit tradeHistoryParamLimit=(TradeHistoryParamLimit)params;
    limit=tradeHistoryParamLimit.getLimit();
  }
  if (params instanceof TradeHistoryParamCurrencyPair) {
    CurrencyPair currencyPair=((TradeHistoryParamCurrencyPair)params).getCurrencyPair();
    if (currencyPair != null)     market=toMarket(currencyPair);
  }
  if (market == null) {
    market=BleutradeTradeHistoryParams.ALL.market;
  }
  if (orderStatus == null) {
    orderStatus=BleutradeTradeHistoryParams.ALL.orderStatus;
  }
  if (orderType == null) {
    orderType=BleutradeTradeHistoryParams.ALL.orderType;
  }
  try {
    BluetradeExecutedTradesWrapper response=bleutrade.getTrades(apiKey,signatureCreator,exchange.getNonceFactory(),market,orderStatus,orderType,limit);
    if (!response.success) {
      throw new ExchangeException(response.message);
    }
    return response.result;
  }
 catch (  BleutradeException e) {
    throw new ExchangeException(e);
  }
}
