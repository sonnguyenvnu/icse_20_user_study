public Map<CurrencyPair,GateioDepth> getGateioDepths() throws IOException {
  Map<String,GateioDepth> depths=bter.getDepths();
  Map<CurrencyPair,GateioDepth> adaptedDepths=new HashMap<>(depths.size());
  depths.forEach((currencyPairString,gateioDepth) -> {
    String[] currencyPairStringSplit=currencyPairString.split("_");
    CurrencyPair currencyPair=new CurrencyPair(Currency.getInstance(currencyPairStringSplit[0].toUpperCase()),Currency.getInstance(currencyPairStringSplit[1].toUpperCase()));
    adaptedDepths.put(currencyPair,gateioDepth);
  }
);
  return adaptedDepths;
}
