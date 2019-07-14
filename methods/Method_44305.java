public GlobitexUserTrades getGlobitexUserTrades(TradeHistoryParamsAll params) throws IOException {
  try {
    return globitex.getTradeHistory(exchange.getExchangeSpecification().getApiKey(),exchange.getNonceFactory(),signatureCreator,"ts",0,params.getLimit(),GlobitexAdapters.adaptCurrencyPairToGlobitexSymbol(params.getCurrencyPair()),exchange.getExchangeSpecification().getUserName());
  }
 catch (  HttpStatusIOException e) {
    throw new ExchangeException(e.getHttpBody());
  }
}
