@Benchmark @Threads(8) public long millis_contention(){
  return System.currentTimeMillis();
}
