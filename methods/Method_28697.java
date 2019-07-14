public void psubscribe(final String... patterns){
  final byte[][] ps=new byte[patterns.length][];
  for (int i=0; i < ps.length; i++) {
    ps[i]=SafeEncoder.encode(patterns[i]);
  }
  psubscribe(ps);
}
