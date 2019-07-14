private BigDecimal getWithdrawalFee(Map<Currency,CurrencyMetaData> currencies,Currency currency,Map<String,AssetDetail> assetDetailMap){
  if (assetDetailMap != null) {
    AssetDetail asset=assetDetailMap.get(currency.getCurrencyCode());
    return asset != null ? asset.getWithdrawFee().stripTrailingZeros() : null;
  }
  return currencies.containsKey(currency) ? currencies.get(currency).getWithdrawalFee() : null;
}
