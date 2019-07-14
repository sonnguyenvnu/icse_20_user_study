public List<Ticker> getAllTickers() throws IOException {
  List<CmcTicker> cmcTickerList=new ArrayList<>();
  try {
    cmcTickerList=super.getCmcLatestDataForAllCurrencies();
  }
 catch (  HttpStatusIOException ex) {
    CmcErrorAdapter.adapt(ex);
  }
  return CmcAdapter.adaptTickerList(cmcTickerList);
}
