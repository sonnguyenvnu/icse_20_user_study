@Override public MappeableContainer flip(short i){
  final int x=toIntUnsigned(i);
  final long bef=bitmap.get(x / 64);
  final long mask=1L << x;
  if (cardinality == MappeableArrayContainer.DEFAULT_MAX_SIZE + 1) {
    if ((bef & mask) != 0) {
      --cardinality;
      bitmap.put(x / 64,bef & ~mask);
      return this.toArrayContainer();
    }
  }
  long aft=bef ^ mask;
  cardinality+=1 - 2 * ((bef & mask) >>> x);
  bitmap.put(x / 64,aft);
  return this;
}
