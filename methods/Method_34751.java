public static RollingCommandMaxConcurrencyStream getInstance(HystrixCommandKey commandKey,int numBuckets,int bucketSizeInMs){
  RollingCommandMaxConcurrencyStream initialStream=streams.get(commandKey.name());
  if (initialStream != null) {
    return initialStream;
  }
 else {
synchronized (RollingCommandMaxConcurrencyStream.class) {
      RollingCommandMaxConcurrencyStream existingStream=streams.get(commandKey.name());
      if (existingStream == null) {
        RollingCommandMaxConcurrencyStream newStream=new RollingCommandMaxConcurrencyStream(commandKey,numBuckets,bucketSizeInMs);
        streams.putIfAbsent(commandKey.name(),newStream);
        return newStream;
      }
 else {
        return existingStream;
      }
    }
  }
}
