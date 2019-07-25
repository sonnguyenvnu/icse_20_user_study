@Benchmark public short backwards(){
  ShortIterator it=container.getReverseShortIterator();
  short min=0;
  while (it.hasNext()) {
    min=it.next();
  }
  return min;
}
