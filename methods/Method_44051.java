public List<CryptopiaMarketHistory> getCryptopiaTrades(CurrencyPair market) throws IOException {
  CryptopiaBaseResponse<List<CryptopiaMarketHistory>> response=cryptopia.getMarketHistory(getPair(market));
  return response.getData();
}
