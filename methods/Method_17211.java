@SuppressWarnings("unchecked") Caffeine<Object,Object> recreateCaffeine(){
  Caffeine<Object,Object> builder=Caffeine.newBuilder();
  if (ticker != null) {
    builder.ticker(ticker);
  }
  if (isRecordingStats) {
    builder.recordStats();
  }
  if (maximumSize != UNSET_INT) {
    builder.maximumSize(maximumSize);
  }
  if (weigher != null) {
    builder.maximumWeight(maximumWeight);
    builder.weigher((Weigher<Object,Object>)weigher);
  }
  if (expiry != null) {
    builder.expireAfter(expiry);
  }
  if (expiresAfterWriteNanos > 0) {
    builder.expireAfterWrite(expiresAfterWriteNanos,TimeUnit.NANOSECONDS);
  }
  if (expiresAfterAccessNanos > 0) {
    builder.expireAfterAccess(expiresAfterAccessNanos,TimeUnit.NANOSECONDS);
  }
  if (refreshAfterWriteNanos > 0) {
    builder.refreshAfterWrite(refreshAfterWriteNanos,TimeUnit.NANOSECONDS);
  }
  if (weakKeys) {
    builder.weakKeys();
  }
  if (weakValues) {
    builder.weakValues();
  }
  if (softValues) {
    builder.softValues();
  }
  if (removalListener != null) {
    builder.removalListener((RemovalListener<Object,Object>)removalListener);
  }
  if ((writer != null) && (writer != CacheWriter.disabledWriter())) {
    builder.writer((CacheWriter<Object,Object>)writer);
  }
  return builder;
}
