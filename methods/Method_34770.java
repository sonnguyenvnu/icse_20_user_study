public static HystrixCommandStartStream getInstance(HystrixCommandKey commandKey){
  HystrixCommandStartStream initialStream=streams.get(commandKey.name());
  if (initialStream != null) {
    return initialStream;
  }
 else {
synchronized (HystrixCommandStartStream.class) {
      HystrixCommandStartStream existingStream=streams.get(commandKey.name());
      if (existingStream == null) {
        HystrixCommandStartStream newStream=new HystrixCommandStartStream(commandKey);
        streams.putIfAbsent(commandKey.name(),newStream);
        return newStream;
      }
 else {
        return existingStream;
      }
    }
  }
}
