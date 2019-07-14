public List<CexIOArchivedOrder> archivedOrders(TradeHistoryParams tradeHistoryParams) throws IOException {
  String baseCcy=null;
  String counterCcy=null;
  Integer limit=null;
  Long dateTo=null;
  Long dateFrom=null;
  Long lastTxDateTo=null;
  Long lastTxDateFrom=null;
  String status;
  if (tradeHistoryParams instanceof CexIOTradeHistoryParams) {
    CexIOTradeHistoryParams params=(CexIOTradeHistoryParams)tradeHistoryParams;
    CurrencyPair currencyPair=params.currencyPair;
    baseCcy=currencyPair == null ? null : currencyPair.base.getCurrencyCode();
    counterCcy=currencyPair == null ? null : currencyPair.counter.getCurrencyCode();
    limit=params.limit;
    dateTo=params.dateTo;
    dateFrom=params.dateFrom;
    lastTxDateTo=params.lastTxDateTo;
    lastTxDateFrom=params.lastTxDateFrom;
    status=params.status;
  }
 else {
    status="d";
    if (tradeHistoryParams instanceof TradeHistoryParamsTimeSpan) {
      TradeHistoryParamsTimeSpan tradeHistoryParamsTimeSpan=(TradeHistoryParamsTimeSpan)tradeHistoryParams;
      lastTxDateFrom=toUnixTimeNullSafe(tradeHistoryParamsTimeSpan.getStartTime());
      lastTxDateTo=toUnixTimeNullSafe(tradeHistoryParamsTimeSpan.getEndTime());
    }
    if (tradeHistoryParams instanceof TradeHistoryParamCurrencyPair) {
      CurrencyPair currencyPair=((TradeHistoryParamCurrencyPair)tradeHistoryParams).getCurrencyPair();
      baseCcy=currencyPair == null ? null : currencyPair.base.getCurrencyCode();
      counterCcy=currencyPair == null ? null : currencyPair.counter.getCurrencyCode();
    }
    if (tradeHistoryParams instanceof TradeHistoryParamLimit) {
      limit=((TradeHistoryParamLimit)tradeHistoryParams).getLimit();
    }
    if (tradeHistoryParams instanceof TradeHistoryParamPaging) {
      limit=((TradeHistoryParamPaging)tradeHistoryParams).getPageLength();
    }
  }
  ArchivedOrdersRequest request=new ArchivedOrdersRequest(limit,dateFrom,dateTo,lastTxDateFrom,lastTxDateTo,status);
  return cexIOAuthenticated.archivedOrders(signatureCreator,baseCcy,counterCcy,request);
}
