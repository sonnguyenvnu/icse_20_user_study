@Override public boolean intersects(int minimum,int supremum){
  if ((minimum < 0) || (supremum < minimum) || (supremum > (1 << 16))) {
    throw new RuntimeException("This should never happen (bug).");
  }
  int pos=Util.unsignedBinarySearch(content,0,cardinality,(short)minimum);
  int index=pos >= 0 ? pos : -pos - 1;
  return index < cardinality && toIntUnsigned(content[index]) < supremum;
}
