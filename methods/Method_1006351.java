@Override public boolean contains(final short i){
  final int x=Util.toIntUnsigned(i);
  return (bitmap[x / 64] & (1L << x)) != 0;
}
