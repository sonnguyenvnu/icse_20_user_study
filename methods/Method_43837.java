public Map<String,CmcCurrencyInfo> getCmcMultipleCurrencyInfo(List<Currency> currencyList) throws IOException {
  List<String> currencyCodes=currencyList.stream().map(Currency::getCurrencyCode).collect(Collectors.toList());
  String commaSeparatedCurrencyCodes=StringUtils.join(currencyCodes,",");
  CmcCurrencyInfoResponse response=null;
  try {
    response=cmcAuthenticated.getCurrencyInfo(apiKey,commaSeparatedCurrencyCodes);
  }
 catch (  HttpStatusIOException ex) {
    CmcErrorAdapter.adapt(ex);
  }
  return response.getData();
}
