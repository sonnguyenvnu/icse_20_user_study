@Override public Container add(final short i){
  final int x=Util.toIntUnsigned(i);
  final long previous=bitmap[x / 64];
  long newval=previous | (1L << x);
  bitmap[x / 64]=newval;
  if (USE_BRANCHLESS) {
    cardinality+=(previous ^ newval) >>> x;
  }
 else   if (previous != newval) {
    ++cardinality;
  }
  return this;
}
