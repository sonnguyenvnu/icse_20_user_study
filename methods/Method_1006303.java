@Benchmark public int iterate(){
  int blackhole=0;
  PeekableIntIterator it=bitmap.getIntIterator();
  while (it.hasNext()) {
    blackhole^=it.next();
  }
  return blackhole;
}
