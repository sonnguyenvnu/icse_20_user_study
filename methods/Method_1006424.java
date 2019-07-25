@Override public boolean contains(int minimum,int supremum){
  int start=minimum >>> 6;
  int end=supremum >>> 6;
  long first=~((1L << minimum) - 1);
  long last=((1L << supremum) - 1);
  if (start == end) {
    return ((bitmap.get(end) & first & last) == (first & last));
  }
  if ((bitmap.get(start) & first) != first) {
    return false;
  }
  if (end < bitmap.limit() && (bitmap.get(end) & last) != last) {
    return false;
  }
  for (int i=start + 1; i < bitmap.limit() && i < end; ++i) {
    if (bitmap.get(i) != -1L) {
      return false;
    }
  }
  return true;
}
