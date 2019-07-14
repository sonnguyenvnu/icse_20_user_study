public List<ANXWalletHistoryEntry> getWalletHistory(TradeHistoryParams params) throws IOException {
  String currencyCode=null;
  if (params instanceof TradeHistoryParamCurrency) {
    Currency currency=((TradeHistoryParamCurrency)params).getCurrency();
    currencyCode=currency == null ? null : currency.getCurrencyCode();
  }
  Integer pageNumber=null;
  if (params instanceof TradeHistoryParamPaging) {
    pageNumber=((TradeHistoryParamPaging)params).getPageNumber();
  }
  boolean userSpecifiedPageNumber=pageNumber != null;
  Date from=null;
  Date to=null;
  if (params instanceof TradeHistoryParamsTimeSpan) {
    TradeHistoryParamsTimeSpan tradeHistoryParamsTimeSpan=(TradeHistoryParamsTimeSpan)params;
    from=tradeHistoryParamsTimeSpan.getStartTime();
    to=tradeHistoryParamsTimeSpan.getEndTime();
  }
  List<ANXWalletHistoryEntry> all=new ArrayList<>();
  ANXWalletHistory walletHistory=getWalletHistory(currencyCode,pageNumber,from,to);
  all.addAll(Arrays.asList(walletHistory.getANXWalletHistoryEntries()));
  while (walletHistory.getRecords() == walletHistory.getMaxResults() && !userSpecifiedPageNumber) {
    pageNumber=walletHistory.getCurrentPage() + 1;
    walletHistory=getWalletHistory(currencyCode,pageNumber,from,to);
    all.addAll(Arrays.asList(walletHistory.getANXWalletHistoryEntries()));
  }
  return all;
}
