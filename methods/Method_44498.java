public List<TradeHistoryResponse> getKucoinTrades(CurrencyPair pair) throws IOException {
  return classifyingExceptions(() -> historyApi.getTradeHistories(KucoinAdapters.adaptCurrencyPair(pair)));
}
