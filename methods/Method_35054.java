private static <Key,Value>Cache<Key,Value> createBaseInFlighter(MemoryPolicy memoryPolicy){
  long expireAfterToSeconds=memoryPolicy == null ? StoreDefaults.getCacheTTLTimeUnit().toSeconds(StoreDefaults.getCacheTTL()) : memoryPolicy.getExpireAfterTimeUnit().toSeconds(memoryPolicy.getExpireAfterWrite());
  long maximumInFlightRequestsDuration=TimeUnit.MINUTES.toSeconds(1);
  if (expireAfterToSeconds > maximumInFlightRequestsDuration) {
    return CacheBuilder.newBuilder().expireAfterWrite(maximumInFlightRequestsDuration,TimeUnit.SECONDS).build();
  }
 else {
    long expireAfter=memoryPolicy == null ? StoreDefaults.getCacheTTL() : memoryPolicy.getExpireAfterWrite();
    TimeUnit expireAfterUnit=memoryPolicy == null ? StoreDefaults.getCacheTTLTimeUnit() : memoryPolicy.getExpireAfterTimeUnit();
    return CacheBuilder.newBuilder().expireAfterWrite(expireAfter,expireAfterUnit).build();
  }
}
