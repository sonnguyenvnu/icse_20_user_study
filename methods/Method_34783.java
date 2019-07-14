public static HystrixThreadPoolStartStream getInstance(HystrixThreadPoolKey threadPoolKey){
  HystrixThreadPoolStartStream initialStream=streams.get(threadPoolKey.name());
  if (initialStream != null) {
    return initialStream;
  }
 else {
synchronized (HystrixThreadPoolStartStream.class) {
      HystrixThreadPoolStartStream existingStream=streams.get(threadPoolKey.name());
      if (existingStream == null) {
        HystrixThreadPoolStartStream newStream=new HystrixThreadPoolStartStream(threadPoolKey);
        streams.putIfAbsent(threadPoolKey.name(),newStream);
        return newStream;
      }
 else {
        return existingStream;
      }
    }
  }
}
