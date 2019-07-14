@Override public void invalidate(StaticBuffer key,List<CachableStaticBuffer> entries){
  Preconditions.checkArgument(!hasValidateKeysOnly() || entries.isEmpty());
  expiredKeys.put(key,getExpirationTime());
  if (Math.random() < 1.0 / INVALIDATE_KEY_FRACTION_PENALTY)   penaltyCountdown.countDown();
}
