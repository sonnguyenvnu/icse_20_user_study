public List<BinanceTicker24h> ticker24h() throws IOException {
  List<BinanceTicker24h> binanceTicker24hList=binance.ticker24h();
  return binanceTicker24hList;
}
