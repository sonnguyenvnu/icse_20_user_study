public static CurrencyPairMetaData toMarketMetaData(WexPairInfo info,WexMetaData wexMetaData){
  int priceScale=info.getDecimals();
  BigDecimal minimumAmount=withScale(info.getMinAmount(),wexMetaData.amountScale);
  BigDecimal feeFraction=info.getFee().movePointLeft(2);
  return new CurrencyPairMetaData(feeFraction,minimumAmount,null,priceScale,null);
}
