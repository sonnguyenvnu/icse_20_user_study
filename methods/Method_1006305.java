@Benchmark public short forwards(){
  PeekableShortIterator it=container.getShortIterator();
  short max=0;
  while (it.hasNext()) {
    max=it.next();
  }
  return max;
}
