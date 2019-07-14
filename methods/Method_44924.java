public Map<Long,WexTransHistoryResult> transactionsHistory(TradeHistoryParams params) throws IOException {
  Long since=null;
  Long from=null;
  if (params instanceof TradeHistoryParamsTimeSpan) {
    final TradeHistoryParamsTimeSpan timeSpanParam=(TradeHistoryParamsTimeSpan)params;
    since=timeSpanParam.getStartTime().toInstant().getEpochSecond();
  }
  if (params instanceof TradeHistoryParamOffset) {
    from=((TradeHistoryParamOffset)params).getOffset();
  }
  WexTransHistoryReturn info=btce.TransHistory(apiKey,signatureCreator,exchange.getNonceFactory(),from,null,null,null,null,since,null);
  checkResult(info);
  Map<Long,WexTransHistoryResult> map=info.getReturnValue();
  return map == null ? Collections.emptyMap() : map;
}
