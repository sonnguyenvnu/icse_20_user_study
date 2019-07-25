@Override public boolean contains(final short x){
  return Util.unsignedBinarySearch(content,0,cardinality,x) >= 0;
}
