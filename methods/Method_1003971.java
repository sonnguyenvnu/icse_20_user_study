public long uptime(){
  return TimeUnit.SECONDS.convert(System.nanoTime() - serverStartNanos,TimeUnit.NANOSECONDS);
}
