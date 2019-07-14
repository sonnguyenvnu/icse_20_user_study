public KunaAskBid getKunaOrderBook(CurrencyPair pair) throws IOException {
  KunaAskBid askBid;
  try {
    askBid=getKuna().getOrders(KunaUtils.toPairString(pair));
  }
 catch (  KunaException e) {
    throw new ExchangeException(e.getMessage());
  }
  return askBid;
}
