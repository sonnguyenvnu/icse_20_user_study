public static CurrencyPairMetaData toMarketMetaData(DSXPairInfo info){
  int priceScale=info.getDecimalsPrice();
  BigDecimal minimumAmount=withScale(info.getMinAmount(),info.getDecimalVolume());
  BigDecimal maximumAmount=withScale(info.getMaxPrice(),info.getDecimalVolume());
  BigDecimal feeFraction=info.getFee().movePointLeft(2);
  return new CurrencyPairMetaData(feeFraction,minimumAmount,maximumAmount,priceScale,null);
}
