public void punsubscribe(final String... patterns){
  final byte[][] ps=new byte[patterns.length][];
  for (int i=0; i < ps.length; i++) {
    ps[i]=SafeEncoder.encode(patterns[i]);
  }
  punsubscribe(ps);
}
