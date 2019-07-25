@Benchmark public void local(){
  BenchmarkContainer small=data.small[index];
  BenchmarkContainer big=data.big[index];
  BufferUtil.unsignedLocalIntersect2by2(small.content,small.length,big.content,big.length,data.dest);
}
