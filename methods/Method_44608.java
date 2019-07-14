public static LimitOrder adaptLimitOrder(List<CurrencyPair> currencyPairList,LykkeOrder lykkeOrder) throws IOException {
  return new LimitOrder(getOrderTypeFromVolumeSign(lykkeOrder.getVolume()),new BigDecimal(Math.abs(lykkeOrder.getVolume())).setScale(8,RoundingMode.HALF_EVEN).stripTrailingZeros(),adaptToCurrencyPair(currencyPairList,lykkeOrder.getAssetPairId()),lykkeOrder.getId(),DateUtils.fromISO8601DateString(lykkeOrder.getCreatedAt()),new BigDecimal(lykkeOrder.getPrice()).setScale(8,RoundingMode.HALF_EVEN).stripTrailingZeros());
}
