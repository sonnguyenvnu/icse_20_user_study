public List<IRippleTradeTransaction> getTradesForAccount(final TradeHistoryParams params,final String account) throws RippleException, IOException {
  final Integer pageLength;
  final Integer pageNumber;
  if (params instanceof TradeHistoryParamPaging) {
    final TradeHistoryParamPaging pagingParams=(TradeHistoryParamPaging)params;
    pageLength=pagingParams.getPageLength();
    pageNumber=pagingParams.getPageNumber();
  }
 else {
    pageLength=pageNumber=null;
  }
  final Collection<String> currencyFilter=new HashSet<>();
  if (params instanceof TradeHistoryParamCurrencyPair) {
    final CurrencyPair pair=((TradeHistoryParamCurrencyPair)params).getCurrencyPair();
    if (pair != null) {
      currencyFilter.add(pair.base.getCurrencyCode());
      currencyFilter.add(pair.counter.getCurrencyCode());
    }
  }
  final Date startTime, endTime;
  if (params instanceof TradeHistoryParamsTimeSpan) {
    final TradeHistoryParamsTimeSpan timeSpanParams=(TradeHistoryParamsTimeSpan)params;
    startTime=timeSpanParams.getStartTime();
    endTime=timeSpanParams.getEndTime();
  }
 else {
    startTime=endTime=null;
  }
  final RippleTradeHistoryCount rippleCount;
  if (params instanceof RippleTradeHistoryCount) {
    rippleCount=(RippleTradeHistoryCount)params;
  }
 else {
    rippleCount=null;
  }
  final String hashLimit;
  if (params instanceof RippleTradeHistoryHashLimit) {
    hashLimit=((RippleTradeHistoryHashLimit)params).getHashLimit();
  }
 else {
    hashLimit=null;
  }
  final List<IRippleTradeTransaction> trades=new ArrayList<>();
  final RippleNotifications notifications=ripplePublic.notifications(account,EXCLUDE_FAILED,EARLIEST_FIRST,pageLength,pageNumber,START_LEDGER,END_LEDGER);
  if (rippleCount != null) {
    rippleCount.incrementApiCallCount();
  }
  if (notifications.getNotifications().isEmpty()) {
    return trades;
  }
  final ListIterator<RippleNotification> iterator=notifications.getNotifications().listIterator(notifications.getNotifications().size());
  while (iterator.hasPrevious()) {
    if (rippleCount != null) {
      if (rippleCount.getTradeCountLimit() > 0 && rippleCount.getTradeCount() >= rippleCount.getTradeCountLimit()) {
        return trades;
      }
      if (rippleCount.getApiCallCountLimit() > 0 && rippleCount.getApiCallCount() >= rippleCount.getApiCallCountLimit()) {
        return trades;
      }
    }
    final RippleNotification notification=iterator.previous();
    if ((endTime != null) && notification.getTimestamp().after(endTime)) {
      continue;
    }
    if ((startTime != null) && notification.getTimestamp().before(startTime)) {
      return trades;
    }
    if (notification.getType().equals("order")) {
    }
 else     if (notification.getType().equals("payment") && notification.getDirection().equals("passthrough")) {
    }
 else {
      continue;
    }
    final IRippleTradeTransaction trade=getTrade(account,notification);
    if (rippleCount != null) {
      rippleCount.incrementApiCallCount();
    }
    if (trade == null) {
      continue;
    }
    final List<RippleAmount> balanceChanges=trade.getBalanceChanges();
    if (balanceChanges.size() < 2 || balanceChanges.size() > 3) {
      continue;
    }
    if (currencyFilter.isEmpty() || (currencyFilter.contains(balanceChanges.get(0).getCurrency()) && currencyFilter.contains(balanceChanges.get(1).getCurrency()))) {
      trades.add(trade);
      if (rippleCount != null) {
        rippleCount.incrementTradeCount();
      }
    }
    if (trade.getHash().equals(hashLimit)) {
      return trades;
    }
  }
  if (params instanceof TradeHistoryParamPaging && (hashLimit != null || startTime != null)) {
    final TradeHistoryParamPaging pagingParams=(TradeHistoryParamPaging)params;
    final int currentPage;
    if (pagingParams.getPageNumber() == null) {
      currentPage=1;
    }
 else {
      currentPage=pagingParams.getPageNumber();
    }
    pagingParams.setPageNumber(currentPage + 1);
    trades.addAll(getTradesForAccount(params,account));
  }
  return trades;
}
