@Benchmark public char[] fastBuffer(){
  final FastCharBuffer fastBuffer=new FastCharBuffer();
  for (int i=0; i < size; i++) {
    fastBuffer.append((char)i);
  }
  return fastBuffer.toArray();
}
