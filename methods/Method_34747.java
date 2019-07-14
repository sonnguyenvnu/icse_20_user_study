public static RollingCommandEventCounterStream getInstance(HystrixCommandKey commandKey,int numBuckets,int bucketSizeInMs){
  RollingCommandEventCounterStream initialStream=streams.get(commandKey.name());
  if (initialStream != null) {
    return initialStream;
  }
 else {
synchronized (RollingCommandEventCounterStream.class) {
      RollingCommandEventCounterStream existingStream=streams.get(commandKey.name());
      if (existingStream == null) {
        RollingCommandEventCounterStream newStream=new RollingCommandEventCounterStream(commandKey,numBuckets,bucketSizeInMs,HystrixCommandMetrics.appendEventToBucket,HystrixCommandMetrics.bucketAggregator);
        streams.putIfAbsent(commandKey.name(),newStream);
        return newStream;
      }
 else {
        return existingStream;
      }
    }
  }
}
