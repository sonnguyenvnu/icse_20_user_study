@Override public void sdiff(final String... keys){
  final byte[][] bkeys=new byte[keys.length][];
  for (int i=0; i < bkeys.length; i++) {
    bkeys[i]=SafeEncoder.encode(keys[i]);
  }
  sdiff(bkeys);
}
