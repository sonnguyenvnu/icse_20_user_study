public Map<String,CmcTicker> getCmcLatestQuotes(Set<Currency> baseCurrencySet,Set<Currency> convertCurrencySet) throws IOException {
  List<String> baseSymbols=baseCurrencySet.stream().map(c -> c.getCurrencyCode()).collect(Collectors.toList());
  String commaSeparatedBaseSymbols=StringUtils.join(baseSymbols,",");
  List<String> convertSymbols=convertCurrencySet.stream().map(c -> c.getCurrencyCode()).collect(Collectors.toList());
  String commaSeparatedConvertCurrencies=StringUtils.join(convertSymbols,",");
  CmcTickerResponse response=null;
  try {
    response=cmcAuthenticated.getLatestQuotes(apiKey,commaSeparatedBaseSymbols,commaSeparatedConvertCurrencies);
  }
 catch (  HttpStatusIOException ex) {
    CmcErrorAdapter.adapt(ex);
  }
  return response.getData();
}
