public List<CryptopiaTicker> getCryptopiaMarkets(Currency baseMarket,long hours) throws IOException {
  CryptopiaBaseResponse<List<CryptopiaTicker>> response=cryptopia.getMarkets(baseMarket.getCurrencyCode(),hours);
  return response.getData();
}
