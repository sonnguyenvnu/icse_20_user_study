@Benchmark @Threads(1) public long millis_noContention(){
  return System.currentTimeMillis();
}
