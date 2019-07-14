public CoingiUserTransactionList getTransactions(TradeHistoryParams p) throws IOException {
  try {
    CoingiTradeHistoryParams params=(CoingiTradeHistoryParams)p;
    CoingiTransactionHistoryRequest request=new CoingiTransactionHistoryRequest();
    request.setPageNumber(params.getPageNumber());
    request.setCurrencyPair(params.getCurrencyPair());
    request.setPageSize(params.getPageSize());
    request.setType(params.getType());
    request.setStatus(params.getStatus());
    return getTransactions(request);
  }
 catch (  CoingiException e) {
    throw CoingiErrorAdapter.adapt(e);
  }
}
