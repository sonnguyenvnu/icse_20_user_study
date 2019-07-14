public CoindealOrder placeOrder(LimitOrder limitOrder) throws IOException {
  try {
    return coindeal.placeOrder(basicAuthentication,CoindealAdapters.adaptCurrencyPair(limitOrder.getCurrencyPair()),CoindealAdapters.adaptOrderType(limitOrder.getType()),"limit","GTC",limitOrder.getOriginalAmount().doubleValue(),limitOrder.getLimitPrice().doubleValue());
  }
 catch (  CoindealException e) {
    throw new ExchangeException(e.getMessage());
  }
}
