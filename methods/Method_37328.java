@Benchmark public byte[] fastBuffer(){
  final FastByteBuffer fastBuffer=new FastByteBuffer();
  for (int i=0; i < size; i++) {
    fastBuffer.append((byte)i);
  }
  return fastBuffer.toArray();
}
