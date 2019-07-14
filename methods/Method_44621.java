public String placeLimitOrder(LykkeLimitOrder lykkeLimitOrder) throws IOException {
  try {
    return lykke.postLimitOrder(lykkeLimitOrder,apiKey);
  }
 catch (  LykkeException e) {
    throw new ExchangeException(e.getMessage());
  }
}
