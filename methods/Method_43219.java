@Override public String placeLimitOrder(LimitOrder limitOrder) throws IOException {
  BitMarketTradeResponse response=placeBitMarketOrder(limitOrder);
  return String.valueOf(response.getData().getId());
}
