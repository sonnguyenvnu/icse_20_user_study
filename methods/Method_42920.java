public List<BankeraOrder> cancelAllBankeraOrders() throws IOException {
  try {
    BankeraExchange bankeraExchange=(BankeraExchange)exchange;
    String auth="Bearer " + bankeraExchange.getToken().getAccessToken();
    return bankeraAuthenticated.cancelAllOrders(auth);
  }
 catch (  BankeraException e) {
    throw BankeraAdapters.adaptError(e);
  }
}
