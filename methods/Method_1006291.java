@Benchmark @BenchmarkMode(Mode.AverageTime) @OutputTimeUnit(TimeUnit.MICROSECONDS) public RoaringBitmap justclone(){
  return bitmap1.clone();
}
