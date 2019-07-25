@Override @Nullable public FutureTask<byte[]> consume(@Nullable final InputStream inputStream,@Nullable String threadName){
  if (inputStream == null) {
    return null;
  }
  Callable<byte[]> callable=new Callable<byte[]>(){
    @Override public byte[] call() throws Exception {
      byte[] result=IOUtils.toByteArray(inputStream);
      if (LOGGER.isTraceEnabled()) {
        LOGGER.trace("Captured {} bytes of process output",result.length);
      }
      return result;
    }
  }
;
  FutureTask<byte[]> result=new FutureTask<byte[]>(callable);
  Thread runner;
  if (isBlank(threadName)) {
    runner=new Thread(result);
  }
 else {
    runner=new Thread(result,threadName);
  }
  runner.start();
  return result;
}
