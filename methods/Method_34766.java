public static HystrixCommandCompletionStream getInstance(HystrixCommandKey commandKey){
  HystrixCommandCompletionStream initialStream=streams.get(commandKey.name());
  if (initialStream != null) {
    return initialStream;
  }
 else {
synchronized (HystrixCommandCompletionStream.class) {
      HystrixCommandCompletionStream existingStream=streams.get(commandKey.name());
      if (existingStream == null) {
        HystrixCommandCompletionStream newStream=new HystrixCommandCompletionStream(commandKey);
        streams.putIfAbsent(commandKey.name(),newStream);
        return newStream;
      }
 else {
        return existingStream;
      }
    }
  }
}
