public List<CryptopiaTicker> getCryptopiaMarkets() throws IOException {
  CryptopiaBaseResponse<List<CryptopiaTicker>> response=cryptopia.getMarkets();
  return response.getData();
}
