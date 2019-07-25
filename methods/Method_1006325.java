@Benchmark public void local(){
  BenchmarkContainer small=data.small[index];
  BenchmarkContainer big=data.big[index];
  Util.unsignedLocalIntersect2by2(small.content,small.length,big.content,big.length,data.dest);
}
