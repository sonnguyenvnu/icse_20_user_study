@Override public boolean intersects(int minimum,int supremum){
  if ((minimum < 0) || (supremum < minimum) || (supremum > (1 << 16))) {
    throw new RuntimeException("This should never happen (bug).");
  }
  int start=minimum >>> 6;
  int end=supremum >>> 6;
  if (start == end) {
    return ((bitmap.get(start) & (~((1L << minimum) - 1) & ((1L << supremum) - 1))) != 0);
  }
  if ((bitmap.get(start) & ~((1L << minimum) - 1)) != 0) {
    return true;
  }
  if (end < bitmap.limit() && (bitmap.get(end) & ((1L << supremum) - 1)) != 0) {
    return true;
  }
  for (int i=1 + start; i < end && i < bitmap.limit(); ++i) {
    if (bitmap.get(i) != 0) {
      return true;
    }
  }
  return false;
}
