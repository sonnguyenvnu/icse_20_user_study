@Benchmark @BenchmarkMode(Mode.AverageTime) public int onefunction(){
  int answer=0;
  for (int k=0; k < bitmap.length; ++k)   answer+=selectBitPosition(bitmap[k],key[k]);
  return answer;
}
