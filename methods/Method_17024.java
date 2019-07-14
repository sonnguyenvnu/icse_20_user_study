@Benchmark @Threads(1) public long nanos_noContention(){
  return System.nanoTime();
}
