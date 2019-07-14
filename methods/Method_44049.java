public List<CryptopiaTicker> getCryptopiaMarkets(Currency baseMarket) throws IOException {
  CryptopiaBaseResponse<List<CryptopiaTicker>> response=cryptopia.getMarkets(baseMarket.getCurrencyCode());
  return response.getData();
}
