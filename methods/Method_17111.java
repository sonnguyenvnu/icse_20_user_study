/** 
 * Creates a serialization proxy based on the common configuration shared by all cache types. 
 */
static <K,V>SerializationProxy<K,V> makeSerializationProxy(BoundedLocalCache<?,?> cache,boolean isWeighted){
  SerializationProxy<K,V> proxy=new SerializationProxy<>();
  proxy.weakKeys=cache.collectKeys();
  proxy.weakValues=cache.nodeFactory.weakValues();
  proxy.softValues=cache.nodeFactory.softValues();
  proxy.isRecordingStats=cache.isRecordingStats();
  proxy.removalListener=cache.removalListener();
  proxy.ticker=cache.expirationTicker();
  proxy.writer=cache.writer;
  if (cache.expiresAfterAccess()) {
    proxy.expiresAfterAccessNanos=cache.expiresAfterAccessNanos();
  }
  if (cache.expiresAfterWrite()) {
    proxy.expiresAfterWriteNanos=cache.expiresAfterWriteNanos();
  }
  if (cache.expiresVariable()) {
    proxy.expiry=cache.expiry();
  }
  if (cache.evicts()) {
    if (isWeighted) {
      proxy.weigher=cache.weigher;
      proxy.maximumWeight=cache.maximum();
    }
 else {
      proxy.maximumSize=cache.maximum();
    }
  }
  return proxy;
}
