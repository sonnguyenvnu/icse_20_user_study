public List<CryptopiaMarketHistory> getCryptopiaTrades(CurrencyPair market,long hours) throws IOException {
  CryptopiaBaseResponse<List<CryptopiaMarketHistory>> response=cryptopia.getMarketHistory(getPair(market),hours);
  return response.getData();
}
