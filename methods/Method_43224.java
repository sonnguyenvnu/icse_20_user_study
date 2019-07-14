private static CurrencyPairMetaData adaptPair(BitmexTicker ticker,CurrencyPairMetaData originalMeta){
  if (originalMeta != null) {
    return new CurrencyPairMetaData(ticker.getTakerFee(),originalMeta.getMinimumAmount(),originalMeta.getMaximumAmount(),Math.max(0,ticker.getTickSize().stripTrailingZeros().scale()),originalMeta.getFeeTiers());
  }
 else {
    return new CurrencyPairMetaData(ticker.getTakerFee(),null,null,Math.max(0,ticker.getTickSize().stripTrailingZeros().scale()),null);
  }
}
