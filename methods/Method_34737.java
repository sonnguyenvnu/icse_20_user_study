public static CumulativeThreadPoolEventCounterStream getInstance(HystrixThreadPoolKey threadPoolKey,int numBuckets,int bucketSizeInMs){
  CumulativeThreadPoolEventCounterStream initialStream=streams.get(threadPoolKey.name());
  if (initialStream != null) {
    return initialStream;
  }
 else {
synchronized (CumulativeThreadPoolEventCounterStream.class) {
      CumulativeThreadPoolEventCounterStream existingStream=streams.get(threadPoolKey.name());
      if (existingStream == null) {
        CumulativeThreadPoolEventCounterStream newStream=new CumulativeThreadPoolEventCounterStream(threadPoolKey,numBuckets,bucketSizeInMs,HystrixThreadPoolMetrics.appendEventToBucket,HystrixThreadPoolMetrics.counterAggregator);
        streams.putIfAbsent(threadPoolKey.name(),newStream);
        return newStream;
      }
 else {
        return existingStream;
      }
    }
  }
}
