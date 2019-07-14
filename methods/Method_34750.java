public static RollingCommandLatencyDistributionStream getInstance(HystrixCommandKey commandKey,int numBuckets,int bucketSizeInMs){
  RollingCommandLatencyDistributionStream initialStream=streams.get(commandKey.name());
  if (initialStream != null) {
    return initialStream;
  }
 else {
synchronized (RollingCommandLatencyDistributionStream.class) {
      RollingCommandLatencyDistributionStream existingStream=streams.get(commandKey.name());
      if (existingStream == null) {
        RollingCommandLatencyDistributionStream newStream=new RollingCommandLatencyDistributionStream(commandKey,numBuckets,bucketSizeInMs);
        streams.putIfAbsent(commandKey.name(),newStream);
        return newStream;
      }
 else {
        return existingStream;
      }
    }
  }
}
