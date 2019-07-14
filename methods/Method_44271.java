@Override public Map<CurrencyPair,Fee> getDynamicTradingFees() throws IOException {
  GeminiTrailingVolumeResponse volumes=Get30DayTrailingVolumeDescription();
  return GeminiAdapters.AdaptDynamicTradingFees(volumes,allCurrencyPairs);
}
