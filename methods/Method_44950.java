public static ExchangeMetaData adaptToExchangeMetaData(ExchangeMetaData exchangeMetaData,YoBitInfo products){
  Map<CurrencyPair,CurrencyPairMetaData> currencyPairs=exchangeMetaData.getCurrencyPairs();
  Map<Currency,CurrencyMetaData> currencies=exchangeMetaData.getCurrencies();
  YoBitPairs pairs=products.getPairs();
  Map<CurrencyPair,YoBitPair> price=pairs.getPrice();
  for (  Entry<CurrencyPair,YoBitPair> entry : price.entrySet()) {
    CurrencyPair pair=entry.getKey();
    YoBitPair value=entry.getValue();
    BigDecimal minSize=value.getMin_amount();
    Integer priceScale=value.getDecimal_places();
    currencyPairs.put(pair,new CurrencyPairMetaData(value.getFee(),minSize,null,priceScale,new FeeTier[]{new FeeTier(BigDecimal.ZERO,new Fee(value.getFee_seller(),value.getFee_buyer()))}));
    if (!currencies.containsKey(pair.base)) {
      CurrencyMetaData currencyMetaData=exchangeMetaData.getCurrencies().get(pair.base);
      BigDecimal withdrawalFee=currencyMetaData == null ? null : currencyMetaData.getWithdrawalFee();
      currencies.put(pair.base,new CurrencyMetaData(8,withdrawalFee));
    }
    if (!currencies.containsKey(pair.counter)) {
      CurrencyMetaData currencyMetaData=exchangeMetaData.getCurrencies().get(pair.counter);
      CurrencyMetaData withdrawalFee=currencyMetaData == null ? null : new CurrencyMetaData(8,currencyMetaData.getWithdrawalFee());
      currencies.put(pair.counter,withdrawalFee);
    }
  }
  return exchangeMetaData;
}
