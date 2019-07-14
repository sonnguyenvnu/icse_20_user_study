@Benchmark @Threads(8) public long nanos_contention(){
  return System.nanoTime();
}
