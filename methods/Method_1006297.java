@Benchmark @BenchmarkMode(Mode.AverageTime) public int manyfunctions(){
  int answer=0;
  for (int k=0; k < bitmap.length; ++k)   answer+=select(bitmap[k],key[k]);
  return answer;
}
