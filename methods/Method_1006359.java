@Override public boolean intersects(int minimum,int supremum){
  if ((minimum < 0) || (supremum < minimum) || (supremum > (1 << 16))) {
    throw new RuntimeException("This should never happen (bug).");
  }
  int start=minimum >>> 6;
  int end=supremum >>> 6;
  if (start == end) {
    return ((bitmap[end] & (~((1L << minimum) - 1) & ((1L << supremum) - 1))) != 0);
  }
  if ((bitmap[start] & ~((1L << minimum) - 1)) != 0) {
    return true;
  }
  if (end < bitmap.length && (bitmap[end] & ((1L << supremum) - 1)) != 0) {
    return true;
  }
  for (int i=1 + start; i < end && i < bitmap.length; ++i) {
    if (bitmap[i] != 0) {
      return true;
    }
  }
  return false;
}
