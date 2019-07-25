@Override public MappeableContainer remove(final short i){
  final int x=toIntUnsigned(i);
  long X=bitmap.get(x / 64);
  long mask=1L << x;
  if (cardinality == MappeableArrayContainer.DEFAULT_MAX_SIZE + 1) {
    if ((X & mask) != 0) {
      --cardinality;
      bitmap.put(x / 64,X & (~mask));
      return this.toArrayContainer();
    }
  }
  long aft=X & ~(mask);
  cardinality-=(aft - X) >>> 63;
  bitmap.put(x / 64,aft);
  return this;
}
