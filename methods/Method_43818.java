public CoingiOrder getCoingiOrder(CoingiGetOrderRequest request) throws IOException {
  handleAuthentication(request);
  return coingiAuthenticated.getOrderStatus(request);
}
