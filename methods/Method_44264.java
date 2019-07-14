public static Map<CurrencyPair,Fee> AdaptDynamicTradingFees(GeminiTrailingVolumeResponse volumeResponse,List<CurrencyPair> currencyPairs){
  Map<CurrencyPair,Fee> result=new Hashtable<>();
  BigDecimal bpsToFraction=BigDecimal.ONE.divide(BigDecimal.ONE.scaleByPowerOfTen(4),4,RoundingMode.HALF_EVEN);
  Fee feeAcrossCurrencies=new Fee(volumeResponse.apiMakerFeeBPS.multiply(bpsToFraction),volumeResponse.apiTakerFeeBPS.multiply(bpsToFraction));
  for (  CurrencyPair currencyPair : currencyPairs) {
    result.put(currencyPair,feeAcrossCurrencies);
  }
  return result;
}
