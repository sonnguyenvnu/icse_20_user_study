public boolean contains(final BigDecimal value){
  final boolean minMatch;
  if (min == null) {
    minMatch=true;
  }
 else {
    int cmp=value.compareTo(min);
    if (minInclusive) {
      minMatch=cmp == 0 || cmp == 1;
    }
 else {
      minMatch=cmp == 1;
    }
  }
  if (!minMatch) {
    return false;
  }
  final boolean maxMatch;
  if (max == null) {
    maxMatch=true;
  }
 else {
    int cmp=value.compareTo(max);
    if (maxInclusive) {
      maxMatch=cmp == 0 || cmp == -1;
    }
 else {
      maxMatch=cmp == -1;
    }
  }
  if (!maxMatch) {
    return false;
  }
  return true;
}
