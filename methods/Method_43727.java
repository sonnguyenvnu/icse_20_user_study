@Override public String placeStopOrder(StopOrder stopOrder) throws IOException, FundsExceededException {
  CoinbaseProPlaceOrder coinbaseProStopOrder=CoinbaseProAdapters.adaptCoinbaseProStopOrder(stopOrder);
  CoinbaseProIdResponse response=placeCoinbaseProOrder(coinbaseProStopOrder);
  return response.getId();
}
